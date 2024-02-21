package com.example.foodplannerapp.model.RemoteData.RealTimeDB;


import android.util.Log;

import androidx.annotation.NonNull;

import com.example.foodplannerapp.Favorite.Presenter.IFavoritesCallBack;
import com.example.foodplannerapp.model.MealPojo;
import com.example.foodplannerapp.model.RootMeal;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RealTimeDB implements IFavoriteGetter , IFavoriteServices {
    private DatabaseReference dbRef ;
    private static RealTimeDB instance ;

    private RealTimeDB(DatabaseReference dbRef) {
        //EXX12JsXnKNeStbOK3OiguK9Ugs1
        this.dbRef = dbRef;


    }
    public static RealTimeDB getInstance(){
        if(instance==null){
            String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
            instance = new RealTimeDB(FirebaseDatabase.getInstance().getReference("users").child(userId));
        }
        return instance;
    }
    public void addMealToCalendar(String dateSelected , MealPojo selectedMeal){

        dbRef.child("calender").child(dateSelected).setValue(selectedMeal);
    }
    @Override
    public void addMealToFavorite(MealPojo mealId ){
//        String mealKey = dbRef.child("favorite").push().getKey();
//        dbRef.child("mealKey").setValue(mealId);
        dbRef.child("favorite").setValue(mealId);

    }
    public void getMealFromCalender(){

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users/user/calender");

      // Attach a ValueEventListener to the reference
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                MealPojo mealPojo = snapshot.getValue(MealPojo.class);
                Log.d("TAGG", "onDataChange:from calender "+mealPojo.getStrMeal());

                //CallBack
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //callBack
            }
        });

    }
    @Override
    public void getMealFromFavorite(IFavoritesCallBack iFavoritesCallBack){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users/user/favorite");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                RootMeal mealPojo = snapshot.getValue(RootMeal.class);
                iFavoritesCallBack.onSuccess(mealPojo);
                Log.d("TAGG", "onDataChange: from favorite "+mealPojo.getMeals().size());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                iFavoritesCallBack.onFailure(error.getMessage());
            }
        });

    }
    @Override
    public void removeMealFromFav(String mealId){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users/user/favorite");
        ref.removeValue();

    }
    public void removeMealFromCalender(String mealId){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users/user/calender");
        ref.removeValue();
    }
}
