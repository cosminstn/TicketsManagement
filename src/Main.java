import com.stn.tickets.db.dao.models.Artist;
import com.stn.tickets.db.dao.models.User;
import com.stn.tickets.db.dao.services.ArtistDAO;
import com.stn.tickets.db.dao.services.UserDAO;
import com.stn.tickets.enums.ArtistTypes;

import java.text.SimpleDateFormat;

public class Main {

    public static void main(String[] args) throws Exception {

        User firstUser = UserDAO.getInstance().getEntity(1);
        System.out.println(firstUser.getUsername());

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");


        Artist artist = new Artist("Florin Salam", "https://media.publika.md/md/image/201811/w720/florin-salamsotie_01020800.jpg",
                new java.sql.Date(dateFormat.parse("01-10-1971").getTime()), ArtistTypes.SINGER);
        System.out.println(ArtistDAO.getInstance().createEntity(artist));
    }

}
