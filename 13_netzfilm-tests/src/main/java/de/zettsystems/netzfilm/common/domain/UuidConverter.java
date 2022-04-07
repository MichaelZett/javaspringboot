package de.zettsystems.netzfilm.common.domain;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.UUID;

@Converter(autoApply = true)
public class UuidConverter implements AttributeConverter<UUID, String> {

    @Override
    public String convertToDatabaseColumn(UUID uuid) {
        if (uuid == null) {
            return null;
        }

        return uuid.toString();
    }

    @Override
    public UUID convertToEntityAttribute(String dbUuid) {
        if (dbUuid == null || dbUuid.isEmpty()) {
            return null;
        }

        return UUID.fromString(dbUuid);
    }
}