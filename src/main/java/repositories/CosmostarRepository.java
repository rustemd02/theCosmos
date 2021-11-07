package repositories;

import models.Cosmostar;
import models.User;

public interface CosmostarRepository {
    Cosmostar findCardByUser (User user);
    Cosmostar cardInit (User user);
}
