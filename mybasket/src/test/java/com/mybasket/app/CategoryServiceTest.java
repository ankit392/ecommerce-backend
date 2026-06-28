package com.mybasket.app;

import com.mybasket.app.dto.CategoryDto;
import com.mybasket.app.entity.Category;
import com.mybasket.app.exception.ResourceNotFoundException;
import com.mybasket.app.repository.CategoryRepository;
import com.mybasket.app.service.CategoryService;
import com.mybasket.app.service.impl.CategoryServiceImpl;
import net.bytebuddy.implementation.auxiliary.MethodCallProxy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


//@SpringBootTest


@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest{

    @Mock
    private CategoryRepository categoryRepository;

    private CategoryService categoryService;

    private ModelMapper mapper;

    @BeforeEach
    public void setup(){
        mapper = new ModelMapper();
        System.out.println("before each");

        categoryService=new CategoryServiceImpl(categoryRepository,mapper);

    }

    @Test
    public void getAll(){


        Mockito.when(categoryRepository.findAll()).thenReturn(
                List.of(

                        Category.builder()
                                .id(2L)
                                .title("trending")
                                .build()
                        ,
                        Category.builder()
                                .id(3L)
                                .title("Iphone trending")
                                .build()


                )


        );

        List<CategoryDto> all = categoryService.getAll();

        int sz=all.size();



        Assertions.assertNotNull(all);
        Assertions.assertEquals(sz,2);

       // Assertions.assertEquals("trending",all.get(0).getTitle());


        Assertions.assertTrue(all.size()>0);
        Mockito.verify(categoryRepository).findAll();

        CategoryDto dto = all.get(0);
        Assertions.assertEquals("trending",dto.getTitle());




    }

    @Test
    public void getAllEmptyTest(){

        Mockito.when(categoryRepository.findAll()).thenReturn(

            Collections.emptyList()

        );

        List<CategoryDto> all = categoryService.getAll();

        Mockito.verify(categoryRepository,Mockito.times(1)).findAll();
        Assertions.assertTrue(all.isEmpty());


    }
    @Test
     void getAllExceptionTest(){


        Mockito.when(categoryRepository.findAll()).thenThrow(


                new RuntimeException("Db error")
        );



        Assertions.assertThrows(RuntimeException.class,()->{
            categoryService.getAll();
        });




    }


@Test
    public void getByid(){

        Mockito.when(categoryRepository.findById(1L)).thenReturn(

                Optional.of(

                        Category.builder()
                                .title("trending")
                                .id(1L)
                                .build()
                )

        );

    CategoryDto single = categoryService.getSingle(1L);


    Assertions.assertNotNull(single);
    Assertions.assertEquals("trending",single.getTitle());
    Assertions.assertEquals(1L,single.getId());
    Mockito.verify(categoryRepository,Mockito.times(1)).findById(1L);

}


@Test
public void getByIdNotFoundTest(){


        Mockito.when(categoryRepository.findById(1L)).thenReturn(

                Optional.empty()
//                Optional.of(
//
//                        Category.builder()
//                                .title("trending")
//                                .id(1L)
//                                .build()
//                )
        );
//    Optional.of(entity) → use assertEquals(), assertNotNull()
//    Optional.empty() → use assertThrows()

    Assertions.assertThrows(
            ResourceNotFoundException.class,
            () -> categoryService.getSingle(1L)
    );
}



@Test
    public void create(){

    CategoryDto inputDto = new CategoryDto();
    inputDto.setTitle("Electronics");
    inputDto.setImageUrl("abc.png");

    Mockito.when(categoryRepository.save(Mockito.any(Category.class)))
            .thenReturn(

                    Category.builder()
                            .id(1L)
                            .title("Electronics")
                            .imageUrl("abc.png")
                            .build()


            );

    CategoryDto categoryDto = categoryService.create(inputDto);

    Assertions.assertNotNull(categoryDto);
    Assertions.assertEquals(1L,categoryDto.getId());
    Assertions.assertEquals("Electronics",categoryDto.getTitle());
    Assertions.assertEquals("abc.png", categoryDto.getImageUrl());

    Mockito.verify(categoryRepository,Mockito.times(1)).save(Mockito.any(Category.class));


}

    @Test
    public void createExceptionTest(){

        CategoryDto inputDto = new CategoryDto();


        Mockito.when(categoryRepository.save(Mockito.any(Category.class)))
                .thenThrow(

                        new RuntimeException("Db error")

                );

        Assertions.assertThrows(RuntimeException.class,()->{

            categoryService.create(inputDto);
        });



    }
    @Test
    public void createCategoryRepositoryCalledTest() {// repo called once

        CategoryDto inputDto = new CategoryDto();
        inputDto.setTitle("Electronics");

        Mockito.when(categoryRepository.save(Mockito.any(Category.class)))
                .thenReturn(
                        Category.builder()
                                .id(1L)
                                .title("Electronics")
                                .build()
                );

        categoryService.create(inputDto);

        Mockito.verify(categoryRepository, Mockito.times(1))
                .save(Mockito.any(Category.class));

        Mockito.verifyNoMoreInteractions(categoryRepository);
    }


    @Test
    void delete(){


           Mockito.when(categoryRepository.findById(1L)).thenReturn(


                  Optional.of(

                          Category.builder()
                                  .id(1L)
                                  .title("Electronics")
                                  .build()

                  )

           );
           // OR
        // doNothing().when(categoryRepository).delete(1L)

           categoryService.delete(1L);

        Mockito.verify(categoryRepository).findById(1L);

           Mockito.verify(categoryRepository).delete(Mockito.any(Category.class));


    }


//    @Test
//    public void updateCategoryNotFoundTest() {
//
//        CategoryDto inputDto = new CategoryDto();
//        inputDto.setTitle("Updated Category");
//
//        Mockito.when(categoryRepository.findById(1L))
//                .thenReturn(Optional.empty());
//
//        Assertions.assertThrows(
//                ResourceNotFoundException.class,
//                () -> categoryService.update(1L, inputDto)
//        );
//
//        Mockito.verify(categoryRepository)
//                .findById(1L);
//
//        Mockito.verify(categoryRepository, Mockito.never())
//                .save(Mockito.any(Category.class));
//    }

            }
















//
//public class CategoryServiceTest {
//
//
//  //  @Autowired
//
//   private CategoryService categoryService;
//
//    @Test
//    public void getAlltest(){
//
//
//        CategoryRepository categoryRepository = Mockito.mock(CategoryRepository.class);
//        ModelMapper modelMapper=new ModelMapper();
//
//        categoryService= new CategoryServiceImpl(categoryRepository,modelMapper);
//
//
//        Mockito.when(categoryRepository.findAll()).thenReturn(
//
//                List.of(
//
//                Category.builder()
//                        .id(12L)
//                        .title("Trending")
//                        .build()
//                        ,
//
//                        Category.builder()
//                                .id(13L)
//                                .title("phone")
//                                .build(),
//                        Category.builder()
//                                .id(14L)
//                                .title("phone")
//                                .build()
//
//                )
//
//        );
//
//
//        //actual service method
//        List<CategoryDto> all = categoryService.getAll();
//
//        int actualsize=all.size();
//
//        int exp=2;
//
//        Assertions.assertEquals(actualsize,exp);
//
//    }
//
//    @Test
//    public void getSingleByIdTest(){
//
//
//        CategoryRepository categoryRepository = Mockito.mock(CategoryRepository.class);
//        ModelMapper modelMapper=new ModelMapper();
//
//
//
//
//        categoryService= new CategoryServiceImpl(categoryRepository,modelMapper);
//
//        Mockito.when(categoryRepository.findById(1L)).thenReturn(
//
//                Optional.of(
//
//                        Category.builder()
//                                .id(1L)
//                                .title("Trending")
//                                .build()
//                )
//        );
//
//        CategoryDto single = categoryService.getSingle(1L);
//
//
//        //Assertions.assertNotNull(single);
//        Assertions.assertEquals("trending",single.getTitle());
//
//
//    }
//
//
//}
