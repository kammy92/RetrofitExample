package net.simplifiedcoding.androidretrofitexample;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.karman.adapter.ServiceListAdapter;
import com.karman.api.BooksAPI;
import com.karman.api.ServicesAPI;
import com.karman.model.Book;
import com.karman.model.Services;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

//Class having OnItemClickListener to handle the clicks on list
public class MainActivity extends AppCompatActivity implements ListView.OnItemClickListener {

    //Root URL of our web service
    public static final String ROOT_URL = "http://10.0.3.2/testing";

    //Strings to bind with intent will be used to send data to other activity
    public static final String KEY_BOOK_ID = "key_book_id";
    public static final String KEY_BOOK_NAME = "key_book_name";
    public static final String KEY_BOOK_PRICE = "key_book_price";
    public static final String KEY_BOOK_STOCK = "key_book_stock";

    //List view to show data
    private ListView listView;

    //List of type books this list will store type Book which is our data model
    private List<Book> books;

    //List of type books this list will store type Book which is our data model
    private List<Services> servicesList = new ArrayList<Services> ();

    private ServiceListAdapter adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing the listview
        listView = (ListView) findViewById(R.id.listViewBooks);

        //Calling the method that will fetch data
      //  getBooks();
        getServices ();
        //Setting onItemClickListener to listview
        listView.setOnItemClickListener (this);
    }

    private void getBooks(){
        //While the app fetched data we are displaying a progress dialog
        final ProgressDialog loading = ProgressDialog.show(this,"Fetching Data","Please wait...",false,false);

        //Creating a rest adapter
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL)
                .build();

        //Creating an object of our api interface
        BooksAPI api = adapter.create(BooksAPI.class);

        //Defining the method
        api.getBooks (new Callback<List<Book>> () {
            @Override
            public void success (List<Book> list, Response response) {
                //Dismissing the loading progressbar
                loading.dismiss ();

                //Storing the data in our list
                books = list;

                //Calling a method to show the list
                showList ();
            }

            @Override
            public void failure (RetrofitError error) {
                //you can handle the errors here
            }
        });
    }

    //Our method to show list
    private void showList(){
        //String array to store all the book names
        String[] items = new String[books.size()];

        //Traversing through the whole list to get all the names
        for(int i=0; i<books.size(); i++){
            //Storing names to string array
            items[i] = books.get(i).getName();
        }

        //Creating an array adapter for list view
        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.simple_list,items);

        //Setting adapter to listview
        listView.setAdapter (adapter);
    }

    private void getServices () {
        //While the app fetched data we are displaying a progress dialog
        final ProgressDialog loading = ProgressDialog.show (this, "Fetching Data", "Please wait...", false, false);

        //Creating a rest adapter
        RestAdapter adapter = new RestAdapter.Builder ()
                .setEndpoint (ROOT_URL)
                .build ();

        //Creating an object of our api interface
        ServicesAPI api = adapter.create (ServicesAPI.class);

        adapter2 = new ServiceListAdapter (this, servicesList);
        listView.setAdapter (adapter2);

        //Defining the method
        api.getServices ("1", "1", new Callback<Response> () {
            @Override
            public void success (Response body, Response response) {
                //Dismissing the loading progressbar
                loading.dismiss ();

                String json = bodyAsString (body);

                try {
                    JSONObject jsonObj = new JSONObject (json);
                    Log.d ("jsonobject ", "" + jsonObj);
                    JSONArray jsonArray = jsonObj.getJSONArray ("results");
                    int json_array_len = jsonArray.length ();
                    Log.d ("JSON_ARRAY", "" + jsonArray);
                    Log.d ("ARRAY_LENGTH", "" + json_array_len);
                    for (int i = 0; i < json_array_len; i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject (i);
                        Services service = new Services ();
                        service.setApp_flow (jsonObject.getInt ("app_flow"));
                        service.setRole_id (jsonObject.getInt ("role_id"));
                        service.setRole_name (jsonObject.getString ("role_name"));
                        service.setImage (jsonObject.getString ("image"));
                        servicesList.add (service);
                    }
                } catch (JSONException e) {
                    e.printStackTrace ();
                }
                adapter2.notifyDataSetChanged ();

                //         adapter.notifyDataSetChanged ();
                //            services = (List<Services>)jsonArray;


                //services = list;

                //Calling a method to show the list
//                showServices ();
            }

            @Override
            public void failure (RetrofitError error) {
                //you can handle the errors here
            }
        });
    }

    private static String bodyAsString (Response body) {
        //Try to get response body
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder ();
        try {
            reader = new BufferedReader (new InputStreamReader (body.getBody ().in ()));
            String line;
            try {
                while ((line = reader.readLine ()) != null) {
                    sb.append (line);
                }
            } catch (IOException e) {
                e.printStackTrace ();
            }
        } catch (IOException e) {
            e.printStackTrace ();
        }
        return sb.toString ();
    }

    //Our method to show list
    private void showServices () {
        //String array to store all the book names
        String[] items = new String[servicesList.size ()];

        //Traversing through the whole list to get all the names
        for (int i = 0; i < servicesList.size (); i++) {
            //Storing names to string array
            items[i] = servicesList.get (i).getRole_name ();
        }

        //Creating an array adapter for list view
        ArrayAdapter adapter = new ArrayAdapter<String> (this, R.layout.simple_list, items);

        //Setting adapter to listview
        listView.setAdapter (adapter);
    }




    //This method will execute on listitem click
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Creating an intent
   //     Intent intent = new Intent(this, ShowBookDetails.class);

        //Getting the requested book from the list
//        Book book = books.get(position);

        //Adding book details to intent
//        intent.putExtra(KEY_BOOK_ID,book.getBookId());
//        intent.putExtra(KEY_BOOK_NAME,book.getName());
//        intent.putExtra(KEY_BOOK_PRICE,book.getPrice());
//        intent.putExtra(KEY_BOOK_STOCK,book.getInStock());

        //Starting another activity to show book details
//        startActivity(intent);
    }
}
