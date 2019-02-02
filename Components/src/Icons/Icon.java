package Icons;


import Glyphs.Glyph;
import javafx.beans.DefaultProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;


@DefaultProperty("glyph")
public class Icon extends Text {

    final double DEFAULT_SIZE = 30;
    final String DEFAULT_COLOR = "Black";

    String id = null;
    private Glyph glyphIcon = null;
    private Glyph.Font font = null;
    private String colorName = null;





    ObjectProperty<Glyph> glyph = new SimpleObjectProperty<>();
    ObjectProperty<Paint> color = new SimpleObjectProperty<Paint>();
    ObjectProperty<Glyph.Font> glyphFont = new SimpleObjectProperty<>();
    DoubleProperty size = new SimpleDoubleProperty();


    public Icon()
    {
        this(Glyph.Weather.ALIEN);
    }

    public Icon(Glyph glyph)
    {

        bindProperties();
        glyphIcon = glyph;

        setGlyph(glyph);
        setGlyphFont(glyph, DEFAULT_SIZE);



        this.size.set(DEFAULT_SIZE);
        this.color.set(Color.BLACK);
        colorName = DEFAULT_COLOR;

    }

    public Icon(Glyph glyph, String id)
    {
        this(glyph);
        this.id = id;

    }




    public Icon(Glyph glyph, double size)
    {
        bindProperties();

        glyphIcon = glyph;

        setGlyph(glyph);
        setGlyphFont(glyph, size);
        this.size.set(size);
        this.color.set(Color.BLACK);
        colorName = DEFAULT_COLOR;

    }

    public Icon(Glyph glyph, String id, double size)
    {
        this(glyph, size);
        this.id = id;

    }



    public Icon(Glyph glyph, Color color)
    {
        this(glyph);
        setColor(color);

    }


    public Icon(Glyph glyph, String id, Color color)
    {
        this(glyph, color);
        this.id = id;


    }



    public Icon(Glyph glyph, double size, Color color)
    {
        this(glyph, size);
        setColor(color);
    }

    public Icon(Glyph glyph, String id, double size, Color color)
    {
        this(glyph, size, color);
        this.id = id;
    }


    private void bindProperties() {

        //fontProperty().bind(this.glyphFont);
        //textProperty().bind(this.glyph.get().getGlyph());
        fillProperty().bind(color);


        size.addListener(observable -> setSize(size.get()));
        //textFillProperty().bind(color);
    }

    public void setGlyphFont(Glyph glyph, double size)
    {
        glyphFont.set(glyph.getFont());
        javafx.scene.text.Font.loadFont(glyph.getFontStream(), size);
        setFont(new javafx.scene.text.Font(glyph.getFamily(), size));

    }


    public void setGlyphFont(String glyphFont)
    {
        this.glyphFont.set(Glyph.Font.valueOf(glyphFont));
        javafx.scene.text.Font.loadFont(this.glyphFont.get().getFontStream(), size.get());
        setFont(new javafx.scene.text.Font(this.glyphFont.get().getFamily(), size.get()));
        //setGlyph(this.glyphFont.get().getDefaultGlyph());



    }

    public void setGlyphFont(Glyph.Font font)
    {

        this.glyphFont.set(font);
        javafx.scene.text.Font.loadFont(this.glyphFont.get().getFontStream(), size.get());
        setFont(new javafx.scene.text.Font(this.glyphFont.get().getFamily(), size.get()));
        //setGlyph(this.glyphFont.get().getDefaultGlyph());



    }



    public void setGlyph(String glyph) {

        if (getFont().getFamily().equals("FontAwesome"))
        {
            try {
                this.glyph.set(Glyph.FontAwesome.valueOf(glyph));

            }
            catch (IllegalArgumentException e)
            {
                this.glyph.set( Glyph.FontAwesome.ANCHOR);

            }
        }

        else if (getFont().getFamily().equals("Weather Icons"))
        {
            try {
                this.glyph.set(Glyph.Weather.valueOf(glyph));

            }
            catch (IllegalArgumentException e)
            {
                this.glyph.set(Glyph.Weather.ALIEN);

            }
        }

        else if (getFont().getFamily().equals("Material Icons"))
        {
            try {
                this.glyph.set(Glyph.Material.valueOf(glyph));

            }
            catch (IllegalArgumentException e)
            {
                this.glyph.set(Glyph.Material.ADD);

            }
        }


        else if (getFont().getFamily().equals("MaterialDesignIcons"))
        {
            try {
                this.glyph.set(Glyph.MaterialDesign.valueOf(glyph));

            }
            catch (IllegalArgumentException e)
            {
                this.glyph.set(Glyph.MaterialDesign.ACCOUNT);

            }
        }

    }

    public void setGlyph(Glyph glyph) {
        this.glyph.set(glyph);
        setText(glyph.getGlyph());
    }

    public void setSize(double size) {
        if (glyphFont.get() != null) setFont(new javafx.scene.text.Font(glyphFont.get().getFamily(), size));
    }

    public void setColor(Color color) {
        this.color.set(color);


    }

    public void setColor(String color) {
        this.color.set(Color.valueOf(color));
        colorName = color;


    }

    public void setColor(Paint color) {
        this.color.set(color);



    }


    //public SimpleStringProperty glyphProperty() {return new SimpleStringProperty(glyph.toString());}
    //public ObjectProperty<Glyph.Font> glyphFontProperty() {return glyphFont;}
    public ObjectProperty<Paint> colorProperty() {return color;}
    public DoubleProperty sizeProperty() {return size;}


    //public String getGlyph() {return glyph.get().toString();}
    //public Glyph.Font getGlyphFont() {return glyphFont.get();}
    public Paint getColor() {return getFill();}
    public double getSize() {return size.get();}





}
