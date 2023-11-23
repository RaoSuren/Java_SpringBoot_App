package codinpad.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import codinpad.exceptions.ResourceNotFoundException;
import codinpad.models.Category;
import codinpad.payloads.CategoryDTO;
import codinpad.repositories.CategoryRepo;

public class CategoryService
{
    @Autowired
    private CategoryRepo  categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    public CategoryDTO createCategory(CategoryDTO categorydto)
    {
        Category category = this.modelMapper.map(categorydto, Category.class);
        Category addedCat = this.categoryRepo.save(category);
        return this.modelMapper.map(addedCat, CategoryDTO.class);
    }

    public CategoryDTO updateCategory(CategoryDTO categorydto, Integer categoryId)
    {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category ", " catogory Id ", categoryId));

        category.setCategoryTitle(categorydto.getCategoryTitle());
        category.setCategoryDescription(categorydto.getCategoryDescription());

        Category updatedcategory = this.categoryRepo.save(category);
        CategoryDTO updatedDTO = this.modelMapper.map(updatedcategory, CategoryDTO.class);
        return updatedDTO;
    }

    public CategoryDTO getCategory(Integer categoryId)
    {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("user", " Id ", categoryId));

        return this.modelMapper.map(category, CategoryDTO.class);
    }

    public List<CategoryDTO> getCategories()
    {
        List<Category> categories = this.categoryRepo.findAll();
        List<CategoryDTO> categoryDTOs = categories.stream().map(category -> this.modelMapper.map(category, CategoryDTO.class)).collect(Collectors.toList());

        return categoryDTOs;
    }

    public void deleteCategory(Integer categoryId)
    {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("User", " Id ", categoryId));
        this.categoryRepo.delete(category);
    }

}