package repositories;

import models.Cosmostar;
import models.User;

public interface CosmostarRepository {
    Cosmostar findCardByUser (User user);
    Cosmostar findCardById (Long cosmostarId);
    Cosmostar cardInit (User user);
}
