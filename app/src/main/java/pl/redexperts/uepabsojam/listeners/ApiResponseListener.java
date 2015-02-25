package pl.redexperts.uepabsojam.listeners;

public interface ApiResponseListener {

    public void onSuccessApiResponse(String method, String response);

    public void onFailureApiResponse(int statusCode, String response);

}
