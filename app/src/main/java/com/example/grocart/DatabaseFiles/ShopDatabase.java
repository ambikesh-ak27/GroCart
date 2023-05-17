package com.example.grocart.DatabaseFiles;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.grocart.GroceryItem;

import java.util.ArrayList;

@Database(entities = {GroceryItem.class, CartItem.class}, version = 1)
public abstract class ShopDatabase extends RoomDatabase {

    public abstract GroceryItemDao groceryItemDao();
    public abstract CartItemDao cartItemDao();

    private static ShopDatabase instance;

    public static synchronized ShopDatabase getInstance(Context context){
        if (null == instance){
            instance = Room.databaseBuilder(context, ShopDatabase.class, "shop_database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .addCallback(initialCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback initialCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            new InitialAsyncTask(instance).execute();


        }
    };

    private static class InitialAsyncTask extends AsyncTask<Void, Void, Void> {

        private GroceryItemDao groceryItemDao;

        public InitialAsyncTask(ShopDatabase db) {
            this.groceryItemDao = db.groceryItemDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            ArrayList<GroceryItem> allItems = new ArrayList<>();
            GroceryItem milk = new GroceryItem("Milk","Milk is the liquid produced by the mammary glands of mammals, including humans. Breast milk is the preferred food for infants, as it is well-tolerated while their digestive tracts develop and mature."
                    ,"https://www.bigbasket.com/media/uploads/p/xxl/306926-2_4-amul-homogenised-toned-milk.jpg",
                    "Drink",80,10);
            allItems.add(milk);

            GroceryItem iceCream = new GroceryItem("Ice Cream","Ice cream is a frozen dessert, typically made from milk or cream and flavoured with a sweetener, either sugar or an alternative, and a spice, such as cocoa or vanilla, or with fruit such as strawberries or peaches.",
                    "https://encrypted-tbn0.gstatic.com/shopping?q=tbn:ANd9GcQuXAaIY9T-66zeURympyayYl35BCbyRmqyvYf3_6EFJONYrVFhqaNQQWoSylepXDqC6-i6UrhEbl2rGHk-h-dwyUEDj1514UL8Vyg3gidgMTqEKAHnI0IT4w",
                    "Food",245,6);
            allItems.add(iceCream);

            GroceryItem soda = new GroceryItem("Soda", "Type of sweet fizzy drink with bubbles that is not alcoholic",
                    "https://www.jiomart.com/images/product/600x600/491071103/kinley-soda-750-ml-product-images-o491071103-p491071103-0-202203150326.jpg",
                    "Drink",45,12);
            allItems.add(soda);

            GroceryItem shampoo = new GroceryItem("Shampoo", "Shampoo is a hair care product, typically in the form of a viscous liquid, that is used for cleaning hair. Less commonly, shampoo is available in bar form, like a bar of soap. Shampoo is used by applying it to wet hair, massaging the product into the scalp, and then rinsing it out. Some users may follow a shampooing with the use of hair conditioner.",
                    "https://res.cloudinary.com/mtree/image/upload/q_auto,f_auto/HeadandShoulders_PH_MW/9Gq7gblVJdM5RfPkfdp5H/6302bb00431710a9b9abf450a31b73e3/HS_PH_Menthol_Large.jpg",
                    "Cleanser", 179, 9);
            allItems.add(shampoo);

            GroceryItem spaghetti = new GroceryItem("Spaghetti",
                    "Spaghetti is a long, thin, solid, cylindrical pasta. It is a staple food of traditional Italian cuisine. Like other pasta, spaghetti is made of milled wheat and water and sometimes enriched with vitamins and minerals. Italian spaghetti is typically made from durum wheat semolina.",
                    "https://sc01.alicdn.com/kf/UTB8AoDnIJoSdeJk43Owq6ya4XXak.jpg_350x350.jpg",
                    "Food", 210, 6);
            allItems.add(spaghetti);

            GroceryItem soap = new GroceryItem("Soap", "Soap is a salt of a fatty acid[1] used in a variety of cleansing and lubricating products. In a domestic setting, soaps are surfactants usually used for washing, bathing, and other types of housekeeping. In industrial settings, soaps are used as thickeners, components of some lubricants, and precursors to catalysts.",
                    "https://www.londondrugs.com/on/demandware.static/-/Sites-londondrugs-master/default/dwfcbde309/products/L9276163/large/L9276163.JPG",
                    "Cleanser", 136, 14);
            allItems.add(soap);

            GroceryItem juice = new GroceryItem("Juice", "Juice is a drink made from the extraction or pressing of the natural liquid contained in fruit and vegetables. It can also refer to liquids that are flavored with concentrate or other biological food sources, such as meat or seafood, such as clam juice. Juice is commonly consumed as a beverage or used as an ingredient or flavoring in foods or other beverages, as for smoothies. Juice emerged as a popular beverage choice after the development of pasteurization methods enabled its preservation without using fermentation (which is used in wine production)",
                    "https://dg6qn11ynnp6a.cloudfront.net/wp-content/uploads/2015/04/199373.jpg",
                    "Drink", 155, 25);
            allItems.add(juice);

            GroceryItem walnut = new GroceryItem("Walnut", "A walnut is the nut of any tree of the genus Juglans (Family Juglandaceae), particularly the Persian or English walnut, Juglans regia. A walnut is the edible seed of a drupe, and thus not a true botanical nut. It is commonly consumed as a nut.",
                    "https://sc01.alicdn.com/kf/Uc583c440540142d89b55cc6fbde774106/969734566/Uc583c440540142d89b55cc6fbde774106.jpg",
                    "Nuts", 800, 4);
            allItems.add(walnut);

            GroceryItem pistachio = new GroceryItem("Pistachio", "The pistachio (/pɪˈstɑːʃiˌoʊ, -ˈstæ-/, Pistacia vera), a member of the cashew family, is a small tree originating from Central Asia and the Middle East. The tree produces seeds that are widely consumed as food. Pistacia vera often is confused with other species in the genus Pistacia that are also known as pistachio.",
                    "https://sc01.alicdn.com/kf/UTB8kYzuIlahduJk43Jaq6zM8FXaz.jpg",
                    "Nuts", 985, 15);
            allItems.add(pistachio);

            for (GroceryItem g: allItems){
                groceryItemDao.insert(g);
            }
            return null;
        }
    }
}
