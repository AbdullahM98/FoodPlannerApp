package com.example.foodplannerapp.model.LocalDataSource;

import static java.nio.file.attribute.AclEntryPermission.DELETE;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodplannerapp.model.LocalCalenderPojo;
import com.example.foodplannerapp.model.LocalFavoritesPojo;
import com.example.foodplannerapp.model.MealPojo;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface MealDao {
    @Query("SELECt* FROM meal_table")
    Flowable<List<LocalMealPojo>> getAllMeals();
    @Query("DELETE FROM meal_table" )
    void deleteAllMeals();
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Single<Long> addMeal(LocalMealPojo mealPojo);
    @Query ("DELETE FROM meal_table WHERE mealId = :mealId")
    Single<Integer> removeMeal(String mealId);


    @Query ("DELETE FROM favorite_meal_table WHERE meal_id = :mealId")
    Single<Integer> removeMealFromFavorite(String mealId);
    @Query ("DELETE FROM calender_table WHERE meal_id = :mealId")
    Single<Integer> removeMealFromCalender(String mealId);
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Single<Long> addMealToFav(LocalFavoritesPojo favPojo);
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Single<Long> addMealToCalender(LocalCalenderPojo calenderPojo);
    @Query("SELECt* FROM favorite_meal_table")
    Flowable<List<LocalFavoritesPojo>> getAllFavsMeals();
    @Query("SELECt* FROM calender_table WHERE date = :date")
    Flowable<List<LocalCalenderPojo>> getAllMealPlans(String date);
    @Query("DELETE FROM meal_table")
    Single<Integer> resetFavMeals();
    @Query("DELETE FROM calender_table")
    Single<Integer> resetCalenderMeals();
}
