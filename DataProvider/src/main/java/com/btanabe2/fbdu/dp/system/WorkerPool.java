package com.btanabe2.fbdu.dp.system;

import com.btanabe2.fbdu.dp.web.WebRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by Brian on 5/1/15.
 */
public class WorkerPool<InputType, WorkerType, OutputType> {
    private WebRequest webRequest;

    public List<OutputType> doWorkInParallel(List<InputType> inputList) {
        ExecutorService executorService = Executors.newFixedThreadPool(inputList.size());
        List<Future<List<OutputType>>> futureList = new ArrayList<>();
//        inputList.forEach(url -> futureList.add(executorService.submit(new WorkerType(webRequest, url))));


        List<OutputType> myList = new ArrayList();

        return myList;
    }
}
