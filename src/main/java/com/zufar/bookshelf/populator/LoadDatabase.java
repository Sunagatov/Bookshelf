package com.zufar.bookshelf.populator;

import com.zufar.bookshelf.dto.AuthorDTO;
import com.zufar.bookshelf.dto.BookDTO;
import com.zufar.bookshelf.dto.DateDTO;
import com.zufar.bookshelf.entity.Author;
import com.zufar.bookshelf.entity.Book;
import com.zufar.bookshelf.entity.Country;
import com.zufar.bookshelf.repository.CountryRepository;
import com.zufar.bookshelf.service.AuthorService;
import com.zufar.bookshelf.service.BookService;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(AuthorService authorService, BookService bookService, CountryRepository countryRepository) {
        return args -> {
            Country russian_federation = new Country("Russian Federation");
            log.info("Preloading " + countryRepository.save(russian_federation));
            log.info("Preloading " + countryRepository.save(new Country("British India")));
            log.info("Preloading " + countryRepository.save(new Country("USA")));
            log.info("Preloading " + countryRepository.save(new Country("United Kingdom")));
            log.info("Preloading " + countryRepository.save(new Country("Brazil")));
            log.info("Preloading " + countryRepository.save(new Country("Japan")));
            log.info("Preloading " + countryRepository.save(new Country("Canada")));
            log.info("Preloading " + countryRepository.save(new Country("Germany")));
            log.info("Preloading " + countryRepository.save(new Country("France")));

            AuthorDTO orwell = new AuthorDTO();
            orwell.setFullName("Eric Blair Arthur");
            orwell.setNickName("George Orwell");
            orwell.setImageLink("https://mtdata.ru/u19/photoA884/20446828934-0/original.jpg");
            orwell.setBirthday(new DateDTO(1903, 6, 25));
            orwell.setDeathDay(new DateDTO(1950, 1, 21));
            orwell.setCountryId(russian_federation.getId());

            AuthorDTO coelho = new AuthorDTO();
            coelho.setImageLink("https://cloudimages.broadwayworld.com/upload11/1633187/tn-500_wm10184355844.jpg");
            coelho.setFullName("Richard Blair");
            coelho.setNickName("Paulo Coelho");
            coelho.setBirthday(new DateDTO(1926, 2, 20));
            coelho.setDeathDay(new DateDTO(2013, 6, 23));
            coelho.setCountryId(russian_federation.getId());

            AuthorDTO bradbury = new AuthorDTO();
            bradbury.setImageLink("https://cdn.fishki.net/upload/post/2019/06/07/3000736/84232741-custom-3e54d21f7e32261f9866964be484ce23dee6d17f-s800.jpg");
            bradbury.setFullName("Ray");
            bradbury.setNickName("Ray Bradbury");
            bradbury.setBirthday(new DateDTO(1920, 8, 22));
            bradbury.setDeathDay(new DateDTO(2012, 5, 23));
            bradbury.setCountryId(russian_federation.getId());

            AuthorDTO asimov = new AuthorDTO();
            asimov.setImageLink("https://24smi.org/public/media/resize/800x-/celebrity/2017/08/11/WfzUTQ8NvHbj_aizek-azimov.jpg");
            asimov.setFullName("Isaac");
            asimov.setNickName("Isaac Asimov");
            asimov.setBirthday(new DateDTO(1920, 1, 2));
            asimov.setDeathDay(new DateDTO(1992, 4, 6));
            asimov.setCountryId(russian_federation.getId());

            Long orwelId = authorService.save(orwell);
            Long coelhoId = authorService.save(coelho);
            Long bradburyId = authorService.save(bradbury);
            Long asimovId = authorService.save(asimov);

            List<Long> authorsIds = new ArrayList<>();
            authorsIds.add(orwelId);
            authorsIds.add(coelhoId);
            authorsIds.add(bradburyId);
            authorsIds.add(asimovId);

            BookDTO book_1984 = new BookDTO();
            book_1984.setTitle("Nineteen Eighty-Four");
            book_1984.setAuthors(authorsIds);
            book_1984.setImageLink("https://ozon-st.cdn.ngenix.net/multimedia/books_covers/1011018318.jpg");
            book_1984.setPdfLink("https://avidreaders.ru/download/1984.html?f=pdf");
            book_1984.setEpubLink("https://avidreaders.ru/download/1984.html?f=epub");
            book_1984.setFb2Link("https://avidreaders.ru/download/1984.html?f=fb2");
            book_1984.setCountryId(russian_federation.getId());
            book_1984.setPageCount(456);
            book_1984.setPublicationDate(new DateDTO(1934, 6, 8));

            BookDTO book_animalFarm = new BookDTO();
            book_animalFarm.setTitle("Animal farm");
            book_animalFarm.setImageLink("https://i.pinimg.com/736x/81/fd/76/81fd7632c4ed22b07b60e4ba663da153.jpg");
            book_animalFarm.setAuthors(authorsIds);
            book_animalFarm.setPdfLink("https://avidreaders.ru/download/animal-farm-a-fairy-story-and.html?f=pdf");
            book_animalFarm.setEpubLink("https://avidreaders.ru/download/animal-farm-a-fairy-story-and.html?f=epub");
            book_animalFarm.setFb2Link("https://avidreaders.ru/download/animal-farm-a-fairy-story-and.html?f=fb2");
            book_animalFarm.setCountryId(russian_federation.getId());
            book_animalFarm.setPageCount(345);
            book_animalFarm.setPublicationDate(new DateDTO(1931, 11, 23));

            BookDTO book_clergymanDaughter = new BookDTO();
            book_clergymanDaughter.setTitle("A Clergyman's Daughter");
            book_clergymanDaughter.setAuthors(authorsIds);
            book_clergymanDaughter.setImageLink("https://kbimages1-a.akamaihd.net/af813f5a-5e9c-41b0-993f-9e4c93f78a50/1200/1200/False/the-clergyman-s-daughter-2.jpg");
            book_clergymanDaughter.setPdfLink("http://www.arvindguptatoys.com/arvindgupta/orwelldaughter.pdf");
            book_clergymanDaughter.setEpubLink("https://www.globalgreyebooks.com/content/books/ebooks/clergymans-daughter.epub");
            book_clergymanDaughter.setFb2Link("https://readli.net/getfile.php?id=493328&format=fb2");
            book_clergymanDaughter.setCountryId(russian_federation.getId());
            book_clergymanDaughter.setPageCount(935);
            book_clergymanDaughter.setPublicationDate(new DateDTO(1988, 12, 28));

            BookDTO book_veronica = new BookDTO();
            book_veronica.setTitle("Veronika Decides to Die");
            book_veronica.setAuthors(authorsIds);
            book_veronica.setCountryId(russian_federation.getId());
            book_veronica.setImageLink("https://1.bp.blogspot.com/-KV6Bw0vxYAI/Vu6adPzd_vI/AAAAAAAAAtE/uo8-0C1ziMgtTEESJJd1r_fN1_fRPslyw/s1600/paulo_koelo_veronika_reshaet_umeret_audiokniga_mp3_96_kbps.jpg");
            book_veronica.setPdfLink("https://www.google.ru/url?sa=t&rct=j&q=&esrc=s&source=web&cd=2&cad=rja&uact=8&ved=2ahUKEwiY2LbnlKvmAhXSl4sKHaOqCfsQFjABegQIBRAB&url=https%3A%2F%2Fwww.academia.edu%2F38132827%2FVeronika_decides_to_die_by_Paulo_Coelho.pdf&usg=AOvVaw1_wR1za4yPABCO4vJIaxA5");
            book_veronica.setEpubLink("https://avidreaders.ru/download/veronika-decides-to-die.html?f=epub");
            book_veronica.setFb2Link("https://avidreaders.ru/download/veronika-decides-to-die.html?f=fb2");
            book_veronica.setPageCount(353);
            book_veronica.setPublicationDate(new DateDTO(1967, 4, 5));

            BookDTO book_alquimista = new BookDTO();
            book_alquimista.setTitle("The Alchemist");
            book_alquimista.setAuthors(authorsIds);
            book_alquimista.setImageLink("https://www.juggernaut.in/image/filters:format(webp):quality(60)/book_covers/9780062416216.jpg");
            book_animalFarm.setPdfLink("https://avidreaders.ru/download/alhimik.html?f=pdf");
            book_animalFarm.setEpubLink("https://avidreaders.ru/download/alhimik.html?f=epub");
            book_animalFarm.setFb2Link("https://avidreaders.ru/download/alhimik.html?f=fb2");
            book_alquimista.setCountryId(russian_federation.getId());
            book_alquimista.setPageCount(234);
            book_alquimista.setPublicationDate(new DateDTO(1923, 7, 18));

            BookDTO book_451 = new BookDTO();
            book_451.setTitle("Fahrenheit 451");
            book_451.setAuthors(authorsIds);
            book_451.setImageLink("https://ic.pics.livejournal.com/teplenkij/33661402/213471/213471_original.jpg");
            book_451.setPdfLink("https://avidreaders.ru/download/451-gradus-po-farengeytu.html?f=pdf");
            book_451.setEpubLink("https://avidreaders.ru/download/451-gradus-po-farengeytu.html?f=epub");
            book_451.setFb2Link("https://avidreaders.ru/download/451-gradus-po-farengeytu.html?f=fb2");
            book_451.setCountryId(russian_federation.getId());
            book_451.setPageCount(456);
            book_451.setPublicationDate(new DateDTO(1944, 8, 1));

            bookService.save(book_1984);
            bookService.save(book_clergymanDaughter);
            bookService.save(book_animalFarm);
            bookService.save(book_alquimista);
            bookService.save(book_veronica);
        };
    }
}
