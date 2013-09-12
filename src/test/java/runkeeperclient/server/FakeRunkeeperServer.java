package runkeeperclient.server;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Filter;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.SparkStopper;
import spark.utils.SparkUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.setPort;

public class FakeRunkeeperServer {

    public static final int PORT = 9999;

    private static final Logger logger = LoggerFactory.getLogger(FakeRunkeeperServer.class);

    public static void start() {
        start(null);
    }

    public static void start(final String acceptedToken) {
        logger.info("starting FakeRunkeeperServer...");

        setPort(PORT);

        boolean installSecurityFilter = acceptedToken != null;
        if (installSecurityFilter) {
            logger.info("installing security filter");
            before(new Filter(SparkUtils.ALL_PATHS, "*/*") {
                @Override
                public void handle(Request request, Response response) {
                    Object authorizationToken = request.headers("Authorization");
                    String expectedToken = String.format("Bearer %s", acceptedToken);
                    logger.info("authorizationToken=" + authorizationToken);
                    if (!expectedToken.equals(authorizationToken)) {
                        logger.warn("rejecting request");
                        halt(HttpStatus.SC_UNAUTHORIZED, "Unauthorised");
                    }
                }
            });
        }

        get(new Route("/fitnessActivities") {
            @Override
            public Object handle(Request request, Response response) {
                return serveFile("fitnessActivityFeed", response);
            }
        });
        get(new Route("/fitnessActivities/:id") {
            @Override
            public Object handle(Request request, Response response) {
                Integer fitnessActivityId = Integer.valueOf(request.params(":id"));
                return serveFile(String.format("fitnessActivities/%d", fitnessActivityId), response);
            }
        });

        get(new Route("/:resource") {
            @Override
            public Object handle(Request request, Response response) {
                String resource = request.params(":resource");
                return serveFile(resource, response);
            }
        });
    }

    private static Object serveFile(String file, Response response) {
        try {
            String path = String.format("responses/%s.json", file);
            logger.debug("reading path " + path);
            InputStream in = FakeRunkeeperServer.class.getClassLoader().getResourceAsStream(path);
            if (in == null) {
                response.status(HttpStatus.SC_NOT_FOUND);
                String message = String.format("the file %s could not be found", file);
                logger.error(message);
                return message;
            }
            StringWriter writer = new StringWriter();
            IOUtils.copy(in, writer);
            return writer.toString();
        } catch (IOException e) {
            response.status(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            logger.error(e.getMessage(), e);
            return e.toString();
        }
    }

    public static void stop() {
        logger.debug("stopping FakeRunkeeperServer...");
        SparkStopper.stop();
    }

    public static void main(String[] args) {
        start();
    }

}
