package nsi.assd.exam.nsiassdquiz2020.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import nsi.assd.exam.nsiassdquiz2020.R;

import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class AboutUaeActivity extends AppCompatActivity {
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_uae);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("About UAE");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        loadAds();
        WebView webView = findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(true);
        webView.getSettings().setSupportZoom(true);
        String htmlText = " %s ";
        String myData = "<html><body style=\"text-align:justify\"><b>History of UAE</b><p>" +
                "<ul>" +
                "<li>Independence from United kingdom in December 2-1971.</li>" +
                "<li> In early 1960s oil discovered in Abu Dhabi. </li>" +
                "<li>The 1st cargo of crude oil was exported from Abu Dhabi in 1962. </li>" +
                "<li>In December 2, 1971 six of the Emirates united as United Arab Emirates, Ras Al khaimah joined later, in early 1972. </li>" +
                "<li>On November 2004, the UAE's first president Sheikh Zayed Bin Sultan Al Nayan died.</li> <p>" +
                "</ul>" +

                "<b><font size=\"+1.5\">Understanding The United Arab Emirates</font></b> <p>" +
                "\n" + "<b>7 Emirates are:-</b>" +
                "<ol>"+
                "<li>Abu Dhabi</li>  <li>Sharjah</li>" +
                " <li>Umm al Quain.</li> <li> Fujeriah.</li> <li> Dubai.</li> <li> Ajman.</li> <li>Ras Al Khaimah.<p>" +
                "</ol>"+
               "<ol>\n" +
                "<li>The UAE’s National Day is celebrated on December 2nd of each year and marks the unification of the emirates into a federation.</li><p>\n" +
                "<li>The UAE is a federation made up of 7 emirates: Abu Dhabi, Dubai, Sharjah, Ra’s al-Khaimah, Ajman, Umm al-Quwain and Fujairah.</li><p>\n" +
                "<li>The UAE was founded under the visionary leadership of the late Sheikh Zayed bin Sultan Al Nahyan, known as the father of the nation.</li><p>\n" +
                "<li>The UAE has a literacy rate of 93%.</li><p>\n" +
                "<li>The falcon is the UAE’s national symbol. Falconry is at the heart of Emirati culture and is a popular pastime.</li><p>\n" +
                "<li>UAE customers purchased $11.64 billion in US goods in 2010, making it the largest export markets for US goods in the Middle East.</li><p>\n" +
                "<li>Women in the UAE account for about 70 per cent of all university graduates in the country and fill around two thirds of government jobs.</li><p>\n" +
                "<li>The UAE is home to approximately 40 churches and cathedrals, as well as additional places of worship for other faiths.</li><p>\n" +
                "<li>Over 12 million tourists visit the UAE per year; tourism now accounts for over 10% of the nation’s GDP.</li><p>\n" +
                "<li>Saadiyat Island in Abu Dhabi will be home to a new cultural complex, housing eight museums, including the Louvre Abu Dhabi, the largest Guggenheim museum in the world and Zayed National Museum.</li><p>\n" +
                "<li>Masdar City, Abu Dhabi is the world’s first zero-carbon, zero-waste, car-free city.</li><p>\n" +
                "<li>UAE Armed Forces work to improve security and stability alongside US and other coalition forces in Afghanistan. The UAE has provided $1.5 billion dollars over the last decade for rehabilitation and reconstruction programs.</li><p>\n" +
                "<li>The UAE hosts more than 2,000 US military personnel.</li><p>\n" +
                "<li>Dubai is the busiest foreign US Navy port of call and Abu Dhabi is home to the Joint Air Warfare Center, where UAE and US pilots regularly train together.</li><p>\n" +
                "<li>The UAE is only one of three countries – and the only Arab nation – to participate with the US in five coalition actions over the last 20 years: Somalia, Bosnia, First Gulf War, Afghanistan and Libya.</li><p>\n" +
                "<li>The UAE trains women as muftis – Sunni Muslim scholars who interpret Islamic law.</li>\n" +
                "<li>The UAE resident population consists of more than 200 nationalities and while Arabic is the official language, other commonly spoken languages include English, Farsi, Hindi, Urdu.</li><p>\n" +
                "<li>The UAE is a member of the United Nations, the World Trade Organization, the Arab League and the Gulf Cooperation Council.</li><p>\n" +
                "<li>A number of international universities have programs in the UAE, including: the Sorbonne, New York University, John’s Hopkins Bloomberg School of Public Health and the Massachusetts Institute of Technology (MIT).</li><p>\n" +
                "<li>To become a major center for world-class healthcare, the UAE has partnered with leading global institutions, including: the Cleveland Clinic, John’s Hopkins Medical School and Susan G. Komen Breast Cancer Foundation.</li><p>\n" +
                "<li>In 2005, the UAE became the one of the first major oil-producing countries to ratify the Kyoto Protocol to the UN Convention on Climate Change.</li><p>\n" +
                "<li>More than 750 US firms have a presence in the UAE, from Bechtel and ExxonMobil to Starbucks and Cold Stone Creamery.</li><p>\n" +
                "<li>Every state in the United States exports to the UAE, as well as the District of Columbia, Puerto Rico, and the Virgin Islands.</li><p>\n" +
                "<li>The UAE is the largest customer for Boeing commercial passenger aircraft.  Customers include Emirates Airlines, which is the world’s largest operator of 777 aircraft and the only airline to operate every Boeing model type; Etihad Airways (purchasing 45 Boeing 777 and 787 passenger jets valued at $9.4 billion); Dubai Aerospace Enterprise (100 airplanes valued at $10.9 billion); FlyDubai (54 airplanes at a catalog price of $4.2 billion); and RAK Airlines (four airplanes).</li><p>\n" +
                "<li>The UAE has funded disaster relief efforts across the globe: for Tsunami Relief ($100 million); Pakistani Earthquake Relief ($100 million); and Hurricane Katrina Relief ($100 million).</li><p>\n" +
                "<li>There are more than 50 weekly nonstop flights to the UAE from 6 different US cities (Atlanta, Chicago, Houston, Los Angeles, New York, San Francisco, Washington, DC).</li><p>\n" +
                "<li>More than 30,000 Americans reside in the UAE.</li>\n" +
                "<li>Women in the UAE form two-thirds of government sector workers and make up 20% of the diplomatic corps.</li><p>\n" +
                "<li>In 2011, the UAE deployed a dozen fighter jets for combat missions in Libya under NATO command.</li><p>\n" +
                "<li>The Yas Marina Circuit on Yas Island in Abu Dhabi hosts the Formula One Grand Prix.</li><p>\n" +
                "<li>In 2009, the UAE became the first country in the Middle East to host the FIFA Club World Cup tournament.</li><p>\n" +
                "<li>In 2004, Sheikha Lubna Al Quasimi, the Minister of Foreign Trade, was appointed as the UAE’s first woman minister.</li>\n" +
                "<li>Sir Bani Yas, a natural island located off the coast of the UAE’s Western Region, was originally home to Arabia’s largest wildlife reserve and boasts the archeological find of the oldest pre-Islamic Christian site in the UAE.</li><p>\n" +
                "<li>Prior to the 1930s, pearling was Abu Dhabi’s most important industry and primary source of income.</li><p>\n" +
                "<li>In 1998, Sharjah was named the “Cultural Capital of the Arab World” by UNESCO.</li><p>\n" +
                "</ol>" +
                "<b>Multi Culture in UAE.</b><p>\n" +
                "\n" +
               "<ul>\n" +
                "<li>Local UAE citizen -15%</li>\n" +
                "<li>Foreign nationals-85%</li>\n" +
                "<li>224 nationalities in UAE.</li>\n" +
                "<li>23% Arabs from other countries.</li>\n" +
                "<li>32% Indian.</li>\n" +
                "<li>38% others Asian and East.</li>\n" +
                "<li>5% European and North American.</li>\n" +
                "<li>25000 people add residence per month.</li>\n" +
                "<li>Total area of UAEs 83,600 km2</li>\n" +
                "<li>Country's founder <b>H.H Shikh Zayed Bin Sultan Al Nahyan.</b> </li>\n" +
                "</ul>" +
                "       <b>UAE citizens have strong sense of security :-</b><p>\n" +
                "<ol>\n" +
                "<li>There is high police present in public.</li>\n" +
                "<li>Private security is widely used, most building have security guard.</li>\n" +
                "<li>Building owners have an expectation that private security will solve the local problems.</li>\n" +
                "</ol>" +
                "<b>Security company and Guard licensing :-</b><p>\n" +
                "\n" +
                "<b>PSBD</b> :-  Private Security Business Department.<br>\n" +
                "<b>PSCOD</b> :-  Private Security Companies Organization Department.<br>\n" +
                "<b>phone number</b> :- 02-402666 website : www.psbd.ae<br>" +
                "<b>PSBD/PSCOD</b> is a unit of Abu Dhabi police & part of UAE ministry of interior and federal department, The staff of the PSCOD is mixture of police officers & civilian support staff. PSCOD has the office in the 6 state of UAE.<p>" +
                "\n" +
                "<b>PSBD employee is Abu Dhabi police officer, it has 5 specific responsibilities.</b><p>\n" +
                "\n" +
                "1. Approving security companies.<br>\n" +
                "\n" +
                "2. Establishing the standards.<br>\n" +
                "\n" +
                "3. Licensing guard.<br>\n" +
                "\n" +
                "4. Inspection companies and security guards.<br>\n" +
                "\n" +
                "5. Enforcing the private security law.<p>\n" +
                "\n" +
                "<b>Three Responsibility of PSBD/PSCOD :-</b><p>\n" +
                "\n" +
                "1. Examination.<br>\n" +
                "2. Issue licenses.<br>\n" +
                "3. Enforce the law.<p>\n" +
                "<b>What is private security?</b><p>\n" +
                "\n" +
                "Private security is a commercial service providing protection and securing to specific client with a focus on prevention.<p>\n" +
                "<b>Security has two major division</b><br>\n" +
                "\n" +
                "Public- ( Police )  &<br>\n" +
                "Private - Security Companies<p>\n" +
                "<b>Basic duty of  UAE police :-</b> <p>\n" +
                "\n" +
                "1. Protect life.<br>\n" +
                "2. Prevent crime.<br>\n" +
                "3. Enforce law.<br>\n" +
                "4. Conduct Investigation.<p>\n" +
                "<b>Private security :-</b> <p>\n" +
                "\n" +
                "1. In house.<br>\n" +
                "2. contract.<br>\n" +
                "Duty responsibility is protect people, property & information.<br>\n" +
                "\n" +
                "And goal of security guard is the identification and prevention of problems that affect client.<p>\n" +
                "<b>Security have 2 function.</b><p>\n" +
                "\n" +
                "1. <b>Static</b> :- Guards deployed in a fixed place or post.<p>\n" +
                "2. <b>Patrol</b> :- Physical movement of a guard in a specific area.<p>\n" +

                "<b>Methods providing area protection:-</b><p>" +
                "1. Building or perimeter protection.<br>" +
                "2. Alarm or surveillance system.<br>\n" +
                "3. Fire prevention and control system.<br>\n" +
                "4. Emergency and disaster planning.<br>\n" +
                "5. Accident prevention and safety rules.<br>\n" +
                "6. Enforcement of rules and regulation.<p>\n" +
                "<b>How to protect people ?</b><p>\n" +
                "1. Observation of crime against of a person<br>\n" +
                "2. Keeping people out of the hazardous area.<br>\n" +
                "3. Being alert to dangerous situation or hazards.<br>\n" +
                "4. Ensuring a safe environment.<p>\n" +
                "<b>Tool use to protect (PPI)</b><p>\n" +
                "1. Barriers,<br>\n" +
                "2. Surveillance equipment e.g. CCTV.<br>\n" +
                "3. Your observation power,<br>\n" +
                "4. Awareness of your surroundings.<br>\n" +
                "5. Patrol,<br>\n" +
                "6. Search,<br>\n" +
                "7. Crowd control,<br>\n" +
                "8. Alarms.<br>\n" +
                "9. Lock.<p>\n" +
                "<b>Three major ways to protect property:-</b><p>\n" +
                "1. Awareness of your surroundings.<br>\n" +
                "2. Patrols.<br>\n" +
                "3. Searches.<p>\n" +
                "<b>Site order :-</b>A set of requirement for a security at a specific location which contains information about the location and how security is to be provided.<p>\n" +
                "<b>Site order :- General area of responsibility.<br>\n" +
                "Post order:- Specific location at a site.</b>\n" +
                "Site order is also known as post order.<p>\n" +
                "<b>Three part of Report :-</b><p>\n" +
                "\n" +
                "1. Introduction<br>\n" +
                "2. Body<br>\n" +
                "3. Summary<br>\n" +
                "And report writing formula is 5'W/1H.<p>\n" +
                "\n" +
                "<b>Two Important considerations.</b><p>-\n" +
                "\n" +
                "1. Assume you reports could be read by anyone ( except the general public.<br>\n" +
                "2. Don't say anything is a report that you can't justify.<p>\n" +
                "<b>Communication :-</b><p>\n" +
                "\n" +
                "Sending  receiving information by speech writing and messaging is called communication.<br><p>\n" +
                "<b>Three messages of communication.</b><p>\n" +
                "\n" +
                "1. Body language -60%<br>\n" +
                "2. Tone of voice   - 30%   ( How to speak)<br>\n" +
                "3. Word -10%   ( the  word use)<p>\n" +
                "<b>Type of languages.</b><p>\n" +
                "\n" +
                "1. Passive<br>\n" +
                "2. Aggressive<p>\n" +
                "<b>The distance or space between 2 people :-</b><p>\n" +
                "\n" +
                "1. Intimate space 0-2 fit.<br>\n" +
                "2. personal space  2 - 4 fit.<br>\n" +
                "3. Social space    4 -7 fit.<br>\n" +
                "4. Public space    7- 20 fit.<p>\n" +
                "you have no particular right.<p>\n" +
                "\n" +
                "Good listening is as important as good speaking 50% /  50% both together make up effective communication.<p>\n" +
                "<b>Good listening includes :-</b><p>\n" +
                "\n" +
                "1. Focusing on what is being said.<br>\n" +
                "2. Watching t speaker.<br>\n" +
                "3. Asking questions.<br>\n" +
                "4. Showing empathy.<p>\n" +
                "<b>Communication skills :-</b><p>\n" +
                "\n" +
                "1. Verbal.<br>\n" +
                "2. Non verbal.<p>\n" +
                "Effective communication is skill of being able to give someone the Right message in the Right way to ensure that it is Understood as intended.<p>\n" +
                "\n" +
                "<b>Three part of  effective communication:-</b><p>\n" +
                "\n" +
                "1. The right message<br>\n" +
                "2. In the right way<br>\n" +
                "3. Understood as intended.<p>\n" +
                "<b>Dialogue includes :-</b><p>\n" +
                "\n" +
                "1. Speaking clearly.<br>\n" +
                "2. Presenting the right message.<br>\n" +
                "3. Speaking confidently.<br>\n" +
                "4. Listen.<p>\n" +
                "<b>How to established proper presence:-</b><p>\n" +
                "\n" +
                "1. Make eye contact.<br>\n" +
                "2. Be aware of your body language.<br>\n" +
                "3. Ensure that your uniform is at the proper deportment standards.<p>\n" +
                "<b>Negative contacts :-</b><p>\n" +
                "\n" +
                "1. People dissatisfied with your client.<br>\n" +
                "2. People who have had a bad way.<br>\n" +
                "3. People who want to  cause trouble.<br>\n" +
                "4. People upset with the work of security.<p>\n" +
                "<b>Preventing a negative contact from happening.</b><p>\n" +
                "\n" +
                "1. Be willing to help.<br>\n" +
                "2. Explain.<br>\n" +
                "3. Don't make assumption about your customer.<br>\n" +
                "4. Based on age or race.<br><p>\n" +
                "<b>How do you turn a negative in to positive contact :-</b><p>\n" +
                "\n" +
                "1. Act professionally.<br>\n" +
                "2. Be confident in your work.<br>\n" +
                "3 Try and solve person's problems.<p>\n" +
                "<b>The most important tool in dealing with difficult people is your attitude things not to do with difficult people.</b><p>\n" +
                "\n" +
                "1. Be difficult in return.<br>\n" +
                "2. Be sarcastic or rude.<br>\n" +
                "3. Argue<br>\n" +
                "4.Laugh.<p>\n" +
                "<b>Your attitude :-</b><p>\n" +
                "\n" +
                "1. Keep a positive attitude.<br>\n" +
                "2. Difficult people are the minority.<br>\n" +
                "3. Focus on providing good security.<br>\n" +
                "4. You can not eliminate what is out of you control.<br>\n" +
                "5. Be realistic and flexible when you can.<p>\n" +
                "Professionalism:-<p>\n" +
                "\n" +
                "\"A person proper knowledge & skill who can give his best efforts even in worst  situation.\"\n" +
                "Professional :-<p>\n" +
                "\n" +
                "\"A person who does his best job when feeling worst.<br>\"\n" +
                "\"Being professional is a state of mind as much as membership.<p>\"\n" +
                "\n" +
                "Physical /Professional standards can be divided into 3 categories :-<p>\n" +
                "\n" +
                "1.Clothing<br>\n" +
                "2.Grooming<br>\n" +
                "3. Equipment.<p>\n" +
                "<b>Discretion:- </b>\" Discretion is careful use of a good judgment n each individual situation to decide how to act.<p>\n" +
                "\n" +
                "<b>Public relation :-</b> \"The relation between your company & your client for business.<p>\n" +
                "\n" +
                "<b>Integrity and Ethics:-</b><p>\n" +
                "\n" +
                "1. It is fundamentally important that security guards work within boundaries of integrity and ethics.<br>\n" +
                "2. Integrity is one of the most important qualities a S.G can have, client has selected your company and your company has selected you because they believe they will receive reliability , honesty and trust.<p>\n" +
                "<b>Ethics :-</b>  ''knowing the difference between good and bad, right and wrong, recognizing moral duties and obligations.<p>" +
                "\n" +
                "<b>Qualities of integrity:-</b>\n" +
                "1. Reliability<br>\n" +
                "2. Honesty<br>\n" +
                "3. Trust.<p>\n" +
                "\"Impartiality is being fair & equal in all that you do.<br>\n" +
                "Impartiality can be affected by attitudes of discrimination or prejudice.<p>\"\n" +
                "\n" +
                "<b>Impartiality can be effected by-: </b><p>" +
                "\n" +
                "1. Discrimination<br>\n" +
                "2. Prejudice<br>\n" +
                "3. Stereotyping<br>\n" +
                "4. Attitude<br>\n" +
                "5. Faulty perception and also by something worst.<p>\n" +
                "<b>Discrimination:-</b> Unfairness.<p>\n" +
                "\n" +
                "Dealing the people on the base of race religion, color, gender language & background is call discrimination.<br>\n" +
                "Discrimination is treating se people different from other on the basis of race, religion, gender, or ethnic background.<p>\n" +
                "<b>Prejudice:-</b> Narrow mindedness, jealousy.<p>\n" +
                "\n" +
                "1. Pre-judgment about a person or about group.<br>\n" +
                "2. Prejudice comes from prejudge and is bias against someone or something because of a personal belief.<p>\n" +
                "<b>How  you will deal with prejudice:-</b><p>\n" +
                "\n" +
                "1. Ignore it<br>\n" +
                "2. Address it<br>\n" +
                "3. Tell someone about it<br>\n" +
                "4. Changer your own behavior.<p\n" +
                "<b>Stereotyping :-<\b> Fixed ideas.<p>\n" +
                "\n" +
                " \"Stereotyping is an individual behavior that makes assumptions about people or culture as a group.<p>\"\n" +
                " \n" +
                "\n" +
                "\"<b>Don't judge a book by its cover.</b><p>\n" +
                "\n" +
                "*Misconduct comes from not action with integrity and ethics.<p>\n" +
                "\n" +
                "<b>Misconduct:-</b><p>\n" +
                "1.Peer- pressure<br>\n" +
                "2.Opportunity<br>\n" +
                "3.Rationalization<br>\n" +
                "4.Frustration<p>\n" +
                "<b>4C's :-</b><p>\n" +
                "\n" +
                " Team work means working in co-operation toward a common goal.<br>\n" +
                "\n" +
                "Team work can be achieved by remembering 4'C'.<p>\n" +
                "\n" +
                "1. Collaboration<br>\n" +
                "2. co-operation<br>\n" +
                "3. Creativity<br>\n" +
                "4. Clarity.<p>\n" +
                "<b>Deportment:-</b><p>\n" +
                "\n" +
                "A deportment is a way in which guard present themselves to the public and consist of a number factors.\n" +
                "Aspects of deportment :-<p>\n" +
                "\n" +
                "1. Uniform<br>\n" +
                "2. Behavior<br>\n" +
                "3. Posture<br>\n" +
                "4. Demeanor<br>\n" +
                "5. Conduct.<p>\n" +
                "* your uniform represents your company in the world.<br>\n" +
                "\n" +
                "* Your deportment is key to the relationship between you . your employer and the clients.<p>\n" +
                "\n" +
                "<b>Benefit of good deportment:-</b><p>\n" +
                "\n" +
                "1. Shows that the guard is serious about work.<br>\n" +
                "2. Gives an impression that security is taken seriously.<p>\n" +
                "<b>Security Customers are:-</b><p>\n" +
                "\n" +
                "1. The Primary client.<br>\n" +
                "2. Client's of client.(people who deal with your primary client.)<br>\n" +
                "3. The public.<p>\n" +
                "<b>Client needs normally 5 duties from security guards-:</b><p>\n" +
                "1. Security<br>\n" +
                "2. Assistance<br>\n" +
                "3. knowledge<br>\n" +
                "5. Problem solve<br>\n" +
                "6. Positive image.<p>\n" +
                "<b>Exceeding service expectations:-</b> <p>\n" +
                "\n" +
                "1. Be consistent<br>" +
                "2. Be positive<br>\n" +
                "3.. Remember that image is important<br>\n" +
                "4. Customer focused.<br><p>\n" +
                "<b>Crime affect 3 things:-</b><p>\n" +
                "\n" +
                "1. Higher prices for goods<br>\n" +
                "2. Higher insurance cast<br>\n" +
                "3. Personal injury or death.<br></body></Html>";


        webView.loadData(String.format(htmlText, myData), "text/html", "utf-8");
    }

    private void loadAds() {
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitialAd_adUnitId));
        adRequest = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequest);
    }
    @Override
    public void onBackPressed() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
            mInterstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    super.onAdClosed();
                    finish();
                }
            });
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // API 5+ solution
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}