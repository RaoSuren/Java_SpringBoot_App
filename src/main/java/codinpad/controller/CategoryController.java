package codinpad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import codinpad.payloads.ApiResponse;
import codinpad.payloads.CategoryDTO;
import codinpad.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

      @Autowired
      private CategoryService categoryService;


      @PostMapping("/")
      public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categorydto)
      {
          CategoryDTO createCategory = this.categoryService.createCategory(categorydto);

          return new ResponseEntity<CategoryDTO>(createCategory, HttpStatus.OK);
      }

      @PutMapping("/{catId}")
      public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categorydto, @PathVariable Integer catId)
      {
          CategoryDTO updateCategory = this.categoryService.updateCategory(categorydto, catId);

          return new ResponseEntity<CategoryDTO>(updateCategory, HttpStatus.OK);
      } 

      @DeleteMapping("/{catId}")
      public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer catId)
      {
          this.categoryService.deleteCategory(catId);

          return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted successfully", true), HttpStatus.OK);
      }

      @GetMapping("/{catId}")
      public ResponseEntity<CategoryDTO> getCategory(@PathVariable Integer catId)
      {
          CategoryDTO category = this.categoryService.getCategory(catId);

          return new ResponseEntity<CategoryDTO>(category, HttpStatus.OK);
      }

      @GetMapping("/")
      public ResponseEntity<List<CategoryDTO>> getCategories()
      {
          List<CategoryDTO> categories = this.categoryService.getCategories();

          return new ResponseEntity<List<CategoryDTO>>(categories, HttpStatus.OK);
      }

}