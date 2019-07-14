package com.repo.repolist.viewModel.impl;

import android.os.Build;
import com.repo.repolist.dataLayer.HomeAPI;
import com.repo.repolist.model.UserRepoData;
import com.repo.repolist.viewModel.HomeViewModel;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Doha on 09/07/19.
 */
public class HomeViewModelImpl implements HomeViewModel {
    private final HomeAPI homeAPI;

    HomeViewModelImpl(HomeAPI api) {
        this.homeAPI = api;
    }

      @Override
       public Flowable<List<UserRepoData>> getRepoData(String userName) {
           return homeAPI.getRepoData(userName).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).map(new Function<List<UserRepoData>, List<UserRepoData>>() {
               @Override
               public List<UserRepoData> apply(List<UserRepoData> repoData) throws Exception {
                   for (UserRepoData creationDate : repoData) {
                       String repoDate = changeDateFormat(creationDate.getRepoCreationDate());
                       creationDate.setRepoCreationDate(repoDate);
                   }
                   return repoData;
               }
           });
       }

    private String changeDateFormat(String serverDate) {
        String myDateFormat = null;
        String serverDatePattern = "yyyy-MM-dd'T'HH:mm:ss'Z'";
        String localDatePattern = "dd-MM-yyyy";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            DateTimeFormatter fIn = DateTimeFormatter.ofPattern(serverDatePattern, Locale.US);
            LocalDate ld = LocalDate.parse(serverDate, fIn);
            DateTimeFormatter fOut = DateTimeFormatter.ofPattern(localDatePattern, Locale.US);
            myDateFormat = ld.format(fOut);
        } else {
            SimpleDateFormat inputFormat = new SimpleDateFormat(serverDatePattern, Locale.US);
            SimpleDateFormat outputFormat = new SimpleDateFormat(localDatePattern, Locale.US);
            Date date = null;
            try {
                date = inputFormat.parse(serverDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            myDateFormat = outputFormat.format(date);
        }
        return myDateFormat;
    }
}
