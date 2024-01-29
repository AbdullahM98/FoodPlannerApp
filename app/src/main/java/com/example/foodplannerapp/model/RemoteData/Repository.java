package com.example.foodplannerapp.model.RemoteData;

public class Repository implements RepoInterface {

    private static Repository repo ;
    private RemoteDataSource remoteData;

    private Repository(RemoteDataSource remoteData) {

        this.remoteData = remoteData;
    }

    public static Repository getInstance(RemoteDataSource remoteData){
        if (repo == null){
            repo = new Repository(remoteData);
        }
        return repo;
    }


    @Override
    public void getAllCategories(NetworkCallBack networkCallBack) {
        remoteData.getCategoryCall(networkCallBack);
    }
}
