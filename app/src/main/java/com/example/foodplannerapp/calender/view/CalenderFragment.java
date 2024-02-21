package com.example.foodplannerapp.calender.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.example.foodplannerapp.R;
import com.example.foodplannerapp.calender.Presenter.CalenderPresenter;
import com.example.foodplannerapp.calender.Presenter.ICalenderPresenter;
import com.example.foodplannerapp.model.LocalCalenderPojo;
import com.example.foodplannerapp.model.LocalDataSource.LocalDataSource;
import com.example.foodplannerapp.model.RemoteData.RemoteDataSource;
import com.example.foodplannerapp.model.Repositories.Repository;

import java.util.ArrayList;
import java.util.List;


public class CalenderFragment extends Fragment  implements ICalenderView , onRemoveFromCalender{
    CalenderAdapter adapter;
    RecyclerView recyclerView ;
    LinearLayoutManager layoutManager ;
    CalendarView calender ;

    ICalenderPresenter presenter ;
    List<LocalCalenderPojo> calenderPojos;

    public CalenderFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view =  inflater.inflate(R.layout.fragment_calender, container, false);
       presenter = new CalenderPresenter(Repository.getInstance(RemoteDataSource.getInstance(), LocalDataSource.getInstance(this.getContext())),this );
       calender = view.findViewById(R.id.calendarView2);
       recyclerView = view.findViewById(R.id.recyView);
       calenderPojos = new ArrayList<>();
       adapter = new CalenderAdapter(this.getContext(),calenderPojos,this);
       layoutManager = new LinearLayoutManager(this.getContext());
       layoutManager.setOrientation(RecyclerView.VERTICAL);
       recyclerView.setLayoutManager(layoutManager);
       recyclerView.setAdapter(adapter);

       calender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
           @Override
           public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
               String selectedDate = Integer.toString(i)+Integer.toString(i1+1)+Integer.toString(i2);
               presenter.getAllPlans(selectedDate);
           }
       });
        return view;
    }
    @Override
    public void updateList(List<LocalCalenderPojo> localCalenderPojos) {

                   adapter.setList(localCalenderPojos);
                    adapter.notifyDataSetChanged();

        }
        // setList(localCalenderPojos);


    @Override
    public void removeFromCalender(LocalCalenderPojo localCalenderPojo) {
        presenter.removePlan(localCalenderPojo);
    }
}