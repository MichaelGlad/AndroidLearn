package glm.net;

import com.google.gson.Gson;
import com.sun.org.apache.xpath.internal.SourceTree;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Michael on 17/09/2017.
 */
public class RetrofitServlet extends HttpServlet {

    public static final String FULLNAME = "fullName";
    public static final String EMAIL = "email";
    public static final String AGE = "age";
    public static final String IS_BRING_FUEL = "isBringFuel";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Samething Start work");

        InputStream inputStream = request.getInputStream();
        byte[] buffer = new byte[256];


        int actuallyRead;
        StringBuilder stringBuilder = new StringBuilder();
        while ((actuallyRead = inputStream.read(buffer)) != -1) {

            stringBuilder = stringBuilder.append(new String(buffer, 0, actuallyRead));

        }

        try {
            JSONObject jsonRequestObject = new JSONObject(stringBuilder.toString());
            User myUser = new User(jsonRequestObject.getString(FULLNAME), jsonRequestObject.getString(EMAIL), jsonRequestObject.getInt(AGE));


//            Gson myGson = new Gson();
//            User myUser = myGson.fromJson(jsonRequestObject.toString(),User.class);
            if(jsonRequestObject.has(IS_BRING_FUEL)){

                myUser.setBringFuel(jsonRequestObject.getBoolean(IS_BRING_FUEL));

            } else {myUser.setBringFuel(false);}

            System.out.println("FullName: " + myUser.getFullName() + " Bring Fuel: " + myUser.getIsBringFuel());
            myUser.setId(Integer.valueOf(myUser.getAge() * 10));


            JSONObject jsonResponseObject = new JSONObject(myUser);
            System.out.println(jsonResponseObject.toString());
            System.out.println("Booleans " + myUser.getFullName() + " Bring Fuel " + myUser.getIsBringFuel());

            OutputStream outputStream = response.getOutputStream();
            outputStream.write(jsonResponseObject.toString().getBytes());
        } catch (JSONException var11) {
            var11.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
