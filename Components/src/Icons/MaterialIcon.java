package Icons;

import Glyphs.Glyph;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.text.Font;

public class MaterialIcon extends Icon {

    private ObjectProperty<Glyph.Material> glyph = new SimpleObjectProperty<>(Glyph.Material.ADD);

    public MaterialIcon()
    {
        this(Glyph.Material.ADD);

    }

    public MaterialIcon(Glyph.Material glyph) {
        size.set(DEFAULT_SIZE);
        setGlyphFont(glyph.getFont());
        setGlyph(glyph);

    }


    public void setGlyphFont(Glyph.Material glyph) {
        Font.loadFont(Glyph.Font.Material.getFontStream(), size.get());
        setFont(new Font(Glyph.Font.Material.getFamily(), size.get()));
    }

    public ObjectProperty<Glyph.Material> glyphProperty() {return glyph;}
    public Glyph.Material getGlyph() {return glyph.get();}
    public void setGlyph(Glyph.Material glyph) {
        this.glyph.set(glyph);
        setText(this.getGlyph().getGlyph());
    }


}
