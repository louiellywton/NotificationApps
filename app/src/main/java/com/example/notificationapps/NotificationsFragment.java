package com.example.notificationapps;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.messaging.RemoteMessage;

import java.util.ArrayList;
import java.util.List;

public class NotificationsFragment extends Fragment {

    private RecyclerView recyclerView;
    private NotificationAdapter notificationAdapter;
    private List<Notification> notificationList;
    private ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewNotifications);
        progressBar = view.findViewById(R.id.progressBar);

        notificationList = new ArrayList<>();
        notificationAdapter = new NotificationAdapter(requireContext(), notificationList);

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(notificationAdapter);

        fetchNotifications();

        return view;
    }

    private void fetchNotifications() {
        // Show the progress bar
        progressBar.setVisibility(View.VISIBLE);

        // Replace this with your code to fetch notifications from Firebase or any other source
        // For demonstration purposes, we are adding sample notifications
        notificationList.clear(); // Clear the existing notifications

        // Add the FCM notifications to the list
        for (RemoteMessage.Notification notification : MyFirebaseMessagingService.getNotifications()) {
            String title = notification.getTitle();
            String message = notification.getBody();

            Notification notificationItem = new Notification(title, message);
            notificationList.add(notificationItem);
        }

        // Notify the adapter that the data set has changed
        notificationAdapter.notifyDataSetChanged();

        // Hide the progress bar
        progressBar.setVisibility(View.GONE);
    }

}
