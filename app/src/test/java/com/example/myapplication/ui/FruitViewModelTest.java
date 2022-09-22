package com.example.myapplication.ui;

import static org.mockito.Mockito.when;

import android.app.Application;

import com.example.myapplication.client.APIInterface;
import com.example.myapplication.config.FruitApplication;
import com.example.myapplication.pojo.Fruit;
import com.example.myapplication.pojo.FruitBuilder;
import com.example.myapplication.pojo.Result;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@RunWith(JUnit4.class)
public class FruitViewModelTest extends TestCase {

    public Retrofit retrofit;
    List<Fruit> fruitList;
    APIInterface apiInterface;
    String BASE_URL = "https://raw.githubusercontent.com";
    Result result;
    @Mock
    Call<Result> mockCall;
    @Mock
    Response<Result> response;

    public FruitViewModel fruitViewModel;
    public FruitApplication fruitApplication;
    public Application application;

    @Before
    public void setUp() {
        result = new Result();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        fruitList = createListOfFruit();
        apiInterface = retrofit.create(APIInterface.class);
        fruitApplication = Mockito.mock(FruitApplication.class);
        application = Mockito.mock(Application.class);
        fruitViewModel = new FruitViewModel(fruitApplication);
    }

    @Test
    public void testNull() {
        when(apiInterface.doGetFruit()).thenReturn(null);
        assertNotNull(fruitViewModel.getFruit());
        assertTrue(fruitViewModel.getFruit().hasObservers());
    }

    @Test
    public void itFetchesAListOfFruits() {
        result.fruit = fruitList;
        apiInterface.doGetFruit();
        fruitViewModel.getFruit();
    }

    @After
    public void tearDown() throws Exception {
        apiInterface = null;
    }

    private List<Fruit> createListOfFruit() {
        Fruit apple = FruitBuilder.aFruit()
                .withType("apple")
                .withPrice(5)
                .withWeight(66)
                .build();

        Fruit banana = FruitBuilder.aFruit()
                .withType("banana")
                .withPrice(9)
                .withWeight(77)
                .build();

        Fruit kiwi = FruitBuilder.aFruit()
                .withType("kiwi")
                .withPrice(4)
                .withWeight(55)
                .build();

        return List.of(apple, banana, kiwi);
    }
}

