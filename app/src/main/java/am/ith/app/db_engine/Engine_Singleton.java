package am.ith.app.db_engine;

import android.content.Context;

import am.ith.app.web_serviece.model.AppResponse;

public class Engine_Singleton {
    private static  Engine_Singleton engine=null;
    private Services service=null;
    private AppResponse.Metadata metadata;

    public AppResponse.Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(AppResponse.Metadata metadata) {
        this.metadata = metadata;
    }

    public static Engine_Singleton getInstance(){
        return (engine==null)?engine=new Engine_Singleton():engine;
    }
    public Services getServices(Context context){
        return (service==null)?service=new Services(context):service;
    }

}
