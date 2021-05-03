package finalProject.pages;

import lombok.*;

/**
 * Класс для представления карточек в разделе Video
 * в качестве объекта
 *
 * @author Aleksandr Kremlev
 * @version 1.0
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TalkCard {
    private String location = "Not defined";
    private String language = "Not defined";
    private String category = "Not defined";
    private String event = "Not defined";

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof TalkCard))
            return false;
        TalkCard other = (TalkCard) o;
        boolean locationEquals = (this.location == null && other.getLocation() == null)
                || (this.location != null && this.location.contains(other.getLocation()));
        boolean languageEquals = (this.language == null && other.getLanguage() == null)
                || (this.language != null && this.language.contains(other.getLanguage()));
        boolean categoryEquals = (this.category == null && other.getCategory() == null)
                || (this.category != null && this.category.contains(other.getCategory()));
        return locationEquals && languageEquals && categoryEquals;
    }
}
