//package wendydeluca.capstone;
//
//import com.github.javafaker.Faker;
//import org.springframework.boot.CommandLineRunner;
//import wendydeluca.capstone.entities.Traveller;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Locale;
//import java.util.Random;
//
//public class Runner implements CommandLineRunner {
//    @Override
//    public void run(String... args) throws Exception {
//
//        Faker faker = new Faker(Locale.ITALY);
//        List<Traveller> travellersList = Arrays.asList();
//        List<String> travelDestinations = Arrays.asList(
//                "Australia", "Spain", "Germany", "Japan", "Italy", "Sweden", "Norway", "Brazil",
//                "Colombia","Argentina","Perù","Chile","Chine","Vietnam","Laos","Cambogia","Thailand","Indonesia","Malaysia",
//                "Philippines","Mexico","Bolivia","Costa Rica","Guatemala","Nicaragua");
//
//        List<String> travellerDescriptions = Arrays.asList("I am a positive self adventurer who has recently joined workaway for different opportunities and experiences wherever" +
//                " I go, along the way I would love the chance to live like a local and see both rural and metropolitan life, where both have a different feel to them.",
//
//       "I'm quite outgoing & make friends easily. I also like to bring a positive energy with kindness & humour." +
//                " So, if you're looking for a travel buddy feel free to reach out & I'd love to explore places with you! Let's embark on a journey of discovery, laughter, and fun as we uncover the hidden gems together!",
//
//
//                "As an adventurous and geography lover I wish visiting anywhere in the world, I don't mind if it's city or nature, I do enjoy both.\n" +
//                "I am aware of current global issues, and that's the point that incentives me the most to help.",
//
//        "I don't really have a plan where to travel, but for my first journey I would like to stay in Europe. I am looking forward to start travelling, but I'm honestly a little bit scared of this first step. But I wouldn't be here, if I didn't want to achieve my personal goal.\n" +
//                "UPDATE: I did my first journey and I loved it, definitely looking forward to my next destination!",
//
//
//                "Beyond my professional endeavors, I am an avid reader with an insatiable curiosity for the world around me." +
//                " I believe that literature holds the power to broaden perspectives and cultivate empathy, making it a powerful tool for both personal growth and community connection." +
//                " Whether I am exploring the complexities of human nature through fiction or delving into educational materials to enhance my pedagogical approach," +
//                " I am constantly seeking new ways to expand my understanding and enrich my interactions with others.",
//
//        "I am an humble and friendly girl and I always do my best, or at least it’s one of my biggest goal; I could be a little introvert at the beginning but is not always like this I promise ahahah; I traveled a lot so I'd be happy to know new cultures, meet new people :)\n" +
//                "The idea of helping people makes me feel alive, so I am putting myself out there and I hope to not let down anyone.",
//
//                "Meeting new people and immersing myself in different cultures and lifestyles is what I thrive on. In my downtime, " +
//                "I enjoy learning new things, diving into books, strumming my guitar, and hanging out with friends. Cooking, especially baking," +
//                " is a joy for me—I'm trained in patisserie and even mastered sourdough bread!",
//
//        "When I was 16, I already did a year abroad, where I stayed with a lovely hostfamily in Ireland.\n" +
//                "I got the chance to really get to know the Irish culture and experience staying abroad from a more intimate perspective.\n" +
//                "This year has shown me that I’d really like to take part in such a cultural exchange again, where i’d be able to stay with a hostfamily and take part in their day-to-day life.",
//
//        "I feel alive when traveling, meeting people and being in nature (hiking, camping, bonfires, stars, the sea, animals, etc.). And I couldn't live without dance, art (ceramics, painting, etc.) and meditation." +
//                " As a psychologist, I'm passionate about everything that helps us grow and self-develop in an integrated and holistic way.",
//
//
//                "I'm full of energy but I'm responsable: I work hard during the day and go to bed early and willingly in the evening:" +
//                " I follow the rhythm of nature");
//
//        List<String> spokenLanguages= Arrays.asList("Italian","French","Spanish","English","Arabic","Portuguese");
//        List<String> interests = Arrays.asList("Gardening","Surfing","Cooking","Camping","Writing","Dancing","Sailing","Cooking","Painting","DogSitting","BabySitting","Trekking");
//
//
//        Random random = new Random();
//
//        String randomDestination = travelDestinations.get(random.nextInt(travelDestinations.size()));
//        String randomDescriptions = travellerDescriptions.get(random.nextInt(travellerDescriptions.size()));
//        String randomSpokenLanguages = spokenLanguages.get(random.nextInt(spokenLanguages.size()));
//        String randomInterests = interests.get(random.nextInt(interests.size()));
//
//        Traveller traveller = new Traveller(faker.name().firstName(),
//                                            faker.name().lastName(),
//                                            faker.number().numberBetween(20,35),
//                                            faker.internet().emailAddress(),
//                                            faker.avatar().image(),
//                                            faker.nation().nationality(),
//                                            randomDestination,
//                                            randomDescriptions,
//                                            randomSpokenLanguages,
//                                            randomInterests);
//
//
//
//
//
//
//
//
//
//    }
//}
