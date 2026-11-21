package com.compilergroup.minilang;
import java.util.List;

public class ControlFlowChecker {
private final ExpressionTypeChecker 
expressions;
private final List<SemanticError> errors;
private final SemanticAnalyzer analyzer;
public ControlFlowChecker(
ExpressionTypeChecker expressions,
List<SemanticError> errors,
SemanticAnalyzer analyzer
) {
this.expressions = expressions;
this.errors = errors;
this.analyzer = analyzer;
}
// Check IF and ELSE statements
public void checkIf(Ast.If statement) {
checkCondition(statement.condition());
analyzer.checkStatements(statement.thenBranc
h());
if (statement.elseBranch() != null) {
analyzer.checkStatements(statement.elseBranch
());
}
}
// Check WHILE loops
public void checkWhile(Ast.While statement) {
checkCondition(statement.condition());
analyzer.checkStatements(statement.body());
}
// Check FOR loops
public void checkFor(Ast.For statement) {
// Check the initial assignment
analyzer.checkStatement(statement.initializer());
// Check the loop condition
checkCondition(statement.condition());
// Check the body
analyzer.checkStatements(statement.body());
// Check the increment assignment
analyzer.checkStatement(statement.increment())
;
}
// Conditions must produce Boolean values
private void checkCondition(Ast.Expr condition) {
ValueType type = expressions.infer(condition);
if (type != ValueType.BOOLEAN
&& type != ValueType.UNKNOWN) {
errors.add(new SemanticError(
null,
"Control-flow conditions must produce a 
Boolean value."
));
}
}
}