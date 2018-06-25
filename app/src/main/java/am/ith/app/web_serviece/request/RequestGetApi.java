package am.ith.app.web_serviece.request;

import am.ith.app.web_serviece.model.AppResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RequestGetApi {
    @GET("/temp//json.php")
    Call<AppResponse> getData();
}