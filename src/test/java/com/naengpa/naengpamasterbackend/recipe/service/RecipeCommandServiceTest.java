package com.naengpa.naengpamasterbackend.recipe.service;

import com.naengpa.naengpamasterbackend.member.entity.Member;
import com.naengpa.naengpamasterbackend.member.repository.MemberRepository;
import com.naengpa.naengpamasterbackend.recipe.dto.request.RecipeUpdateRequest;
import com.naengpa.naengpamasterbackend.recipe.entity.Difficulty;
import com.naengpa.naengpamasterbackend.recipe.entity.Recipe;
import com.naengpa.naengpamasterbackend.recipe.entity.RecipeCategory;
import com.naengpa.naengpamasterbackend.recipe.repository.RecipeCategoryRepository;
import com.naengpa.naengpamasterbackend.recipe.repository.RecipeRepository;
import com.naengpa.naengpamasterbackend.recipe.repository.RecipeRequiredProductRepository;
import com.naengpa.naengpamasterbackend.recipe.repository.RecipeStepRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class RecipeCommandServiceTest {

    @Mock RecipeRepository recipeRepository;
    @Mock RecipeCategoryRepository recipeCategoryRepository;
    @Mock RecipeRequiredProductRepository recipeRequiredProductRepository;
    @Mock RecipeStepRepository recipeStepRepository;
    @Mock MemberRepository memberRepository;

    @InjectMocks
    RecipeCommandService recipeCommandService;

    private static final RecipeUpdateRequest REQUEST =
            new RecipeUpdateRequest("새 이름", "설명", 20, Difficulty.NORMAL, 2L);

    private void givenMember(String email, Long memberId) {
        Member member = Mockito.mock(Member.class);
        given(member.getId()).willReturn(memberId);
        given(memberRepository.findByEmail(email)).willReturn(Optional.of(member));
    }

    private Recipe givenRecipeOwnedBy(Long authorId) {
        Recipe recipe = Recipe.builder()
                .createdBy(authorId).name("기존").difficulty(Difficulty.EASY).build();
        given(recipeRepository.findByRecipeIdAndDeletedFalse(1L)).willReturn(Optional.of(recipe));
        given(recipeCategoryRepository.findById(2L))
                .willReturn(Optional.of(Mockito.mock(RecipeCategory.class)));
        return recipe;
    }

    @Test
    @DisplayName("작성자 본인이면 수정된다")
    void update_byOwner() {
        givenMember("owner@test.com", 7L);
        Recipe recipe = givenRecipeOwnedBy(7L);

        recipeCommandService.updateRecipe(1L, "owner@test.com", false, REQUEST);

        assertThat(recipe.getName()).isEqualTo("새 이름");
    }

    @Test
    @DisplayName("작성자가 아니어도 관리자면 수정된다")
    void update_byAdmin() {
        givenMember("admin@test.com", 1L);
        Recipe recipe = givenRecipeOwnedBy(99L);

        recipeCommandService.updateRecipe(1L, "admin@test.com", true, REQUEST);

        assertThat(recipe.getName()).isEqualTo("새 이름");
    }

    @Test
    @DisplayName("작성자도 관리자도 아니면 403 예외가 발생한다")
    void update_byOther_forbidden() {
        givenMember("other@test.com", 7L);
        givenRecipeOwnedBy(99L);

        assertThatThrownBy(() -> recipeCommandService.updateRecipe(1L, "other@test.com", false, REQUEST))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("403");
    }

    @Test
    @DisplayName("삭제 - 작성자 본인이면 소프트 삭제된다")
    void delete_byOwner() {
        givenMember("owner@test.com", 7L);
        Recipe recipe = givenRecipeOwnedBy(7L);

        recipeCommandService.deleteRecipe(1L, "owner@test.com", false);

        assertThat(recipe.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("삭제 - 관리자면 타인 레시피도 삭제된다")
    void delete_byAdmin() {
        givenMember("admin@test.com", 1L);
        Recipe recipe = givenRecipeOwnedBy(99L);

        recipeCommandService.deleteRecipe(1L, "admin@test.com", true);

        assertThat(recipe.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("삭제 - 작성자도 관리자도 아니면 403 예외가 발생한다")
    void delete_byOther_forbidden() {
        givenMember("other@test.com", 7L);
        givenRecipeOwnedBy(99L);

        assertThatThrownBy(() -> recipeCommandService.deleteRecipe(1L, "other@test.com", false))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("403");
    }
}
