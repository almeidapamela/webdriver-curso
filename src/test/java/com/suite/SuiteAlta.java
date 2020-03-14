package com.suite;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;
import com.categorias.Alta;
import com.test.NavegacaoTest;
import com.test.WebElementsTest;

@RunWith(Categories.class)
@IncludeCategory(Alta.class)
@SuiteClasses({NavegacaoTest.class, WebElementsTest.class })
public class SuiteAlta {

}
