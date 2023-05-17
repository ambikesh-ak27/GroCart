package com.example.grocart;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class MainFragment extends Fragment {
    private BottomNavigationView bottomNavigationView;
    private RecyclerView newItemsRecView, popularItemsRecView, suggestedItemsRecView;
    private GroceryItemAdapter newItemsAdapter, popularItemsAdapter, suggestedItemsAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main,container,false);

        initViews(view);
        initBottomNavView();
//        Utils.clearSharedPreferences(getActivity());
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        initRecViews();
    }

    private void initViews(View view){
        bottomNavigationView = view.findViewById(R.id.bottomNavView);
        newItemsRecView = view.findViewById(R.id.newItemsRecView);
        popularItemsRecView = view.findViewById(R.id.popularItemRecView);
        suggestedItemsRecView = view.findViewById(R.id.suggestedItemsRecView);
    }

    private void initRecViews(){
        newItemsAdapter = new GroceryItemAdapter(getActivity());
        newItemsRecView.setAdapter(newItemsAdapter);
        newItemsRecView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));

        popularItemsAdapter = new GroceryItemAdapter(getActivity());
        popularItemsRecView.setAdapter(popularItemsAdapter);
        popularItemsRecView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));

        suggestedItemsAdapter = new GroceryItemAdapter(getActivity());
        suggestedItemsRecView.setAdapter(suggestedItemsAdapter);
        suggestedItemsRecView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));

        ArrayList<GroceryItem> newItems = Utils.getAllItems(getActivity());
        if (null != newItems){
            Comparator<GroceryItem> newItemComparator = new Comparator<GroceryItem>() {
                @Override
                public int compare(GroceryItem o1, GroceryItem o2) {
                    return o1.getId()-o2.getId();
                }
            };
            Comparator<GroceryItem> reverseComparator = Collections.reverseOrder(newItemComparator);
            Collections.sort(newItems, reverseComparator);
            newItemsAdapter.setItems(newItems);
        }

        ArrayList<GroceryItem> popularItems = Utils.getAllItems(getActivity());
        if (null != popularItems){
            Comparator<GroceryItem> popularItemComparator = new Comparator<GroceryItem>() {
                @Override
                public int compare(GroceryItem o1, GroceryItem o2) {
                    return o1.getPopularityPoint()-o2.getPopularityPoint();
                }
            };
            Collections.sort(popularItems, Collections.reverseOrder(popularItemComparator));
            popularItemsAdapter.setItems(popularItems);
        }

        ArrayList<GroceryItem> suggestedItems = Utils.getAllItems(getActivity());
        if (null != suggestedItems){
            Comparator<GroceryItem> suggestedItemComparator = new Comparator<GroceryItem>() {
                @Override
                public int compare(GroceryItem o1, GroceryItem o2) {
                    return o1.getUserPoint()-o2.getUserPoint();
                }
            };
            Collections.sort(suggestedItems, Collections.reverseOrder(suggestedItemComparator));
            suggestedItemsAdapter.setItems(suggestedItems);
        }
    }

    private void initBottomNavView(){
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.home){

                } else if (id == R.id.search) {
                    Intent intent = new Intent(getActivity(), SearchActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
                else if (id == R.id.cart) {
                    Intent intent = new Intent(getActivity(), CartActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
                return false;
            }
        });
    }
}
