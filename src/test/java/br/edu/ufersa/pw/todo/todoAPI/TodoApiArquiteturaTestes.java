package br.edu.ufersa.pw.todo.todoAPI;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "br.edu.ufersa.pw.todo.todoAPI")
public class TodoApiArquiteturaTestes {
    @ArchTest
    public static final ArchRule classesInternasDevemSerAcessadasApenasPeloProprioModulo =
            classes()
                    .that().resideInAPackage("..todo.internal..")
                    .should().onlyBeAccessed()
                    .byClassesThat().resideInAnyPackage("..todo..");
    @ArchTest
    public static final ArchRule controllersNaoDevemChamarRepositoriesDiretamente =
            noClasses()
                    .that().haveSimpleNameEndingWith("Controller")
                    .should().dependOnClassesThat().haveSimpleNameEndingWith("Repository")
                    .because("Controllers devem orquestrar via Services ou Interfaces de Caso de Uso, nunca tocar direto na persistência.");

    @ArchTest
    public static final ArchRule modulosNaoDevemTerDependenciasCiclicas =
            com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices()
                    .matching("br.edu.ufersa.pw.todo.todoAPI.(*)..")
                    .should().beFreeOfCycles()
                    .because("Dependência circular entre features destrói a modularidade (ex: Todo depende de Usuario que depende de Todo).");
}
