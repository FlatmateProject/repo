package dto;

import common.tableBuilder.ArrayObtained;

public class ArchiveData implements ArrayObtained {

    private long archiveId;

    @Override
    public Object[] getArray() {
        return new Object[]{archiveId};
    }
}
