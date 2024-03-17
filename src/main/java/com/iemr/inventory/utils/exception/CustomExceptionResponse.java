package com.iemr.inventory.utils.exception;

import java.util.Calendar;
import java.util.Date;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.LongSerializationPolicy;
import com.google.gson.annotations.Expose;

@Component
public class CustomExceptionResponse {
    public static final int SUCCESS = 200;
    public static final int GENERIC_FAILURE = 5000;
    public static final int OBJECT_FAILURE = 5001;
    public static final int USERID_FAILURE = 5002;
    public static final int PASSWORD_FAILURE = 5003;
    public static final int PREVILAGE_FAILURE = 5004;
    public static final int CODE_EXCEPTION = 5005;
    public static final int ENVIRONMENT_EXCEPTION = 5006;
    public static final int PARSE_EXCEPTION = 5007;
    public static final int DB_EXCEPTION = 5008;
    public static final int BAD_REQUEST = 400;
    public static final int NOT_FOUND = 404;
    public static final String SUCCESS_SC = "SUCCESS";
    public static final String NOT_FOUND_SC = "NOT_FOUND";
    public static final String DB_EXCEPTION_SC = "DB_EXCEPTION";
    public static final String BAD_REQUEST_SC = "BAD_REQUEST";
    public static final String INTERNAL_SERVER_ERROR_SC = "INTERNAL_SERVER_ERROR";
    public static final String SUCCESS_SC_V = "200";
    public static final String NOT_FOUND_SC_V = "404";
    public static final String DB_EXCEPTION_SC_V = "5008";
    public static final String BAD_REQUEST_SC_V = "400";
    public static final String INTERNAL_SERVER_ERROR_SC_V = "500";
    private static final String RESPONSE = "{\"response\":\"$$STRING\"}";
    private static final String RESPONSE_VALUE = "$$STRING";
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
    @Expose
    private Object data;
    @Expose
    private int statusCode = GENERIC_FAILURE;
    @Expose
    private String errorMessage = "Failed with generic error";
    @Expose
    private String status = "FAILURE";

    public void setResponse(String message) {
        JsonArray ja = null;
        try {
            Object obj = new JsonParser().parse(message);
            if (obj instanceof JsonArray) {
                ja = (JsonArray) obj;
                this.data = ja;
            } else if (obj instanceof JsonObject) {
                this.data = obj;
            } else {
                this.data = new JsonParser().parse(RESPONSE.replace(RESPONSE_VALUE, message));
                // this.data = message;
            }
        } catch (Exception exe) {
            this.data = message;
            this.data = new JsonParser().parse(RESPONSE.replace(RESPONSE_VALUE, message));
        }
        statusCode = SUCCESS;
        errorMessage = "Success";
        status = "Success";

    }

    public void setError(Throwable thrown) {
        Date currDate = Calendar.getInstance().getTime();
        logger.info("error happened due to " + thrown.getClass().getSimpleName() + " at " + currDate.toString());

        switch (thrown.getCause().getClass().getSimpleName()) {
            case "IEMRException":
                this.statusCode = USERID_FAILURE;
                status = "User login failed";
                errorMessage = thrown.getMessage();
                break;
            case "JSONException":
                this.statusCode = OBJECT_FAILURE;
                status = "Invalid object conversion";
                errorMessage = "Invalid object conversion";
                break;

            case "SQLException":
                this.statusCode = DB_EXCEPTION;
                status = DB_EXCEPTION_SC;
                errorMessage = thrown.getMessage();
                break;
            case "SQLGrammarException":
                this.statusCode = DB_EXCEPTION;
                status = DB_EXCEPTION_SC;
                errorMessage = thrown.getMessage();
                break;
            case "DataException":
                this.statusCode = DB_EXCEPTION;
                status = DB_EXCEPTION_SC;
                errorMessage = thrown.getMessage();
                break;
            case "ConstraintViolationException":
                this.statusCode = DB_EXCEPTION;
                status = DB_EXCEPTION_SC;
                errorMessage = thrown.getMessage();
                break;
            case "GenericJDBCException":
                this.statusCode = DB_EXCEPTION;
                status = DB_EXCEPTION_SC;
                errorMessage = thrown.getMessage();
                break;
            case "JDBCConnectionException":
                this.statusCode = DB_EXCEPTION;
                status = DB_EXCEPTION_SC;
                errorMessage = thrown.getMessage();
                break;
            case "LockAcquisitionException":
                this.statusCode = DB_EXCEPTION;
                status = DB_EXCEPTION_SC;
                errorMessage = thrown.getMessage();
                break;
            case "InvalidDataAccessResourceUsageException":
                this.statusCode = DB_EXCEPTION;
                status = DB_EXCEPTION_SC;
                errorMessage = thrown.getMessage();
                break;

            case "ParseException":
            case "NullPointerException":

            case "ArrayIndexOutOfBoundsException":

            case "IOException":
            case "ConnectException":
            case "ConnectIOException":
                this.statusCode = ENVIRONMENT_EXCEPTION;
                status = "Failed with connection issues at " + currDate.toString() + "Please try after some time. "
                        + "If error is still seen,  contact your administrator.";
                errorMessage = thrown.getMessage();
                break;
            case "JDBCException":
                this.statusCode = DB_EXCEPTION;
                status = DB_EXCEPTION_SC;
                errorMessage = thrown.getMessage();
                break;
            default:
                this.statusCode = GENERIC_FAILURE;
                status = "Failed with " + thrown.getMessage() + " at " + currDate.toString()
                        + ".Please try after some time. If error is still seen, contact your administrator.";
                errorMessage = thrown.getMessage();
                break;
        }
        logger.error("Failure happend with " + thrown.getMessage() + "at " + currDate.toString(), thrown);
    }

    public void setError(int errorCode, String message, String status) {
        this.errorMessage = message;
        this.status = status;
        this.statusCode = errorCode;
    }

    public void setError(int errorCode, String message) {
        setError(errorCode, message, message);
    }

    public boolean isSuccess() {
        return this.statusCode == SUCCESS;
    }

    /**
     * @return the data
     */
    public String getData() {
        JSONObject obj = new JSONObject(toString());
        if (obj.has("data")) {
            return obj.get("data").toString();
        } else if (obj.has("response")) {
            return obj.getJSONObject("response").get("data").toString();
        } else {
            return null;
        }
    }

    /**
     * @return the statusCode
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithoutExposeAnnotation();
        builder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
        String output = builder.create().toJson(this);
        return output;

    }

    public String toStringWithSerialization() {
        GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithoutExposeAnnotation();
        builder.serializeNulls();
        return builder.create().toJson(this);
    }

}
