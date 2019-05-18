package com.stn.tickets.db.dao.services;

import com.stn.tickets.db.dao.models.Artist;
import com.stn.tickets.db.dao.services.general.EntityDAO;
import com.stn.tickets.db.engine.PreparedStatementParameter;
import com.stn.tickets.enums.ArtistTypes;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("Duplicates")
public class ArtistDAO extends EntityDAO<Artist> {

    private static ArtistDAO instance;

    private ArtistDAO() {
        super("ARTISTS", "ARTIST_ID", false);
    }

    public static ArtistDAO getInstance() {
        if (instance == null)
            instance = new ArtistDAO();
        return instance;
    }

    @Override
    public Artist getEntity(int id) throws Exception {
        Object[] unparsedObject = getUnparsedEntityById(id);

        int colIndex = 0;

        Artist artist = new Artist();

        artist.setId((Integer) unparsedObject[colIndex++]);
        artist.setName((String) unparsedObject[colIndex++]);
        artist.setProfilePicUrl((String) unparsedObject[colIndex++]);
        artist.setBirthDate((java.sql.Date) unparsedObject[colIndex++]);

        int artistTypeId = (Integer) unparsedObject[colIndex];
        ArtistTypes type = ArtistTypes.getArtistTypeById(artistTypeId);
        artist.setType(type);
        return artist;
    }

    @Override
    public List<String> getColumnsNamesWithoutPK() {
        return Arrays.asList("NAME", "PROFILE_PIC_URL", "BIRTH_DATE", "TYPE_ID");
    }

    @Override
    public Artist castFromObjectArray(Object[] arr) throws Exception {
        return null;
    }

    @Override
    public List<PreparedStatementParameter> castToParamsList(Artist entity, boolean includePK) {
        List<PreparedStatementParameter> params = new ArrayList<>();

        int colIndex = 1;
        if (includePK)
            params.add(new PreparedStatementParameter<Integer>(colIndex++, entity.getId(), Types.INTEGER));
        params.add(new PreparedStatementParameter<String>(colIndex++, entity.getName(), Types.NVARCHAR));
        params.add(new PreparedStatementParameter<String>(colIndex++, entity.getProfilePicUrl(), Types.NVARCHAR));
        params.add(new PreparedStatementParameter<java.sql.Date>(colIndex++, entity.getBirthDate(), Types.DATE));
        params.add(new PreparedStatementParameter<Integer>(colIndex, entity.getType().getId(), Types.INTEGER));

        return params;
    }

    @Override
    public boolean updateEntity(Artist updatedEntity) throws Exception {
        return false;
    }
}
