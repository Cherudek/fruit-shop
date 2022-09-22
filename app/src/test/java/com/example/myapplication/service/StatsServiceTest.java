package com.example.myapplication.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.example.myapplication.client.APIInterface;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


@RunWith(MockitoJUnitRunner.class)
public class StatsServiceTest extends TestCase {

    @Mock
    public APIInterface mockAPIInterface;

    public Call<String> mockCall;

    @Before
    public void setUp() {
        mockCall = Mockito.mock(Call.class);

    }

    @Test
    public void whenEventIsLoadAndData100ShouldReturnStats() {
       when(mockAPIInterface.getStats("load", 100)).thenReturn(mockCall);

        Mockito.doAnswer(invocation -> {
            Callback<String> callback = invocation.getArgument(0, Callback.class);
            callback.onResponse(mockCall, Response.success("[17] 173206"));
            return null;
        }).when(mockCall).enqueue(any(Callback.class));
    }

    @Test
    public void whenEventIsDisplayAndData3000ShouldReturnStats() {
        when(mockAPIInterface.getStats("display", 3000)).thenReturn(mockCall);
        Mockito.doAnswer(invocation -> {
            Callback<String> callback = invocation.getArgument(0, Callback.class);
            callback.onResponse(mockCall, Response.success("[1] 173657"));
            return null;
        }).when(mockCall).enqueue(any(Callback.class));
    }

    @Test
    public void whenEventIsErrorAndDataNPEShouldNotReturnStats() {
        when(mockAPIInterface.getStats("error", 0)).thenReturn(mockCall);
        Mockito.doAnswer(invocation -> {
            Callback<String> callback = invocation.getArgument(0, Callback.class);
            callback.onResponse(mockCall, Response.error(500, mockCall.execute().errorBody()));
            return null;
        }).when(mockCall).enqueue(any(Callback.class));
    }

}