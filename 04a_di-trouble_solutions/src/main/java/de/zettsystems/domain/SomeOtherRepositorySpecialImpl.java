package de.zettsystems.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SomeOtherRepositorySpecialImpl implements SomeOtherRepository {
    private static final Logger LOG = LoggerFactory.getLogger(SomeOtherRepositorySpecialImpl.class);

    @Override
    public void tellMe() {
        LOG.info("What?");
    }
}
