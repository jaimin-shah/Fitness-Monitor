import java.awt.Desktop;
import java.net.URI;

class okTypes
{

    public static void main(String args[]) 
    {

        try 
        {
            // Create Desktop object
            Desktop d=Desktop.getDesktop();

            // Browse a URL, for example www.facebook.com
            d.browse(new URI("https://www.google.co.in/?gfe_rd=cr&ei=VREPVYmNLJXCuAS7koHICQ&gws_rd=ssl#q=")); 
            // This open facebook.com in your default browser.
        }
        catch(Exception ex) 
        {
            ex.printStackTrace();
        }
    }
}