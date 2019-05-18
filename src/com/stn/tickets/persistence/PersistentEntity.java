package com.stn.tickets.persistence;

import static com.stn.tickets.utils.Constants.COMMA_REPLACER;
import static com.stn.tickets.utils.Constants.CSV_FIELD_SEPARATOR;

public abstract class PersistentEntity {

    private String persistenceFileName;

    public PersistentEntity(String fileName) {
        persistenceFileName = fileName;
    }

    public String getPersistenceFileName() {
        return persistenceFileName;
    }

    /**
     * Replaces any comma in this string with ` so it won't be taken for a separator.
     * Also takes care of null Strings
     * @param input
     * @return
     */
    public String filterString(String input) {
        if (input == null)
            return "";
        return input.replace(CSV_FIELD_SEPARATOR, COMMA_REPLACER);
    }

    public abstract String toCsvLine() throws Exception;

    public abstract <T extends PersistentEntity> T loadFromCsvLine(String line) throws Exception;
}
