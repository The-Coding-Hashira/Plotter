package bg.sofia.tu.iti.math.context;

import bg.sofia.tu.iti.math.core.calculator.spec.CalculatorAssociativity;
import bg.sofia.tu.iti.math.core.calculator.spec.CalculatorSpec;
import bg.sofia.tu.iti.math.function.Cosine;
import bg.sofia.tu.iti.math.function.DegreesToRadiansConverter;
import bg.sofia.tu.iti.math.function.Function;
import bg.sofia.tu.iti.math.function.Sine;
import bg.sofia.tu.iti.math.operator.type.OperatorType;
import bg.sofia.tu.iti.math.expression.input.parser.FunctionDefinitionParser;
import bg.sofia.tu.iti.math.expression.input.token.TokenTypeFactory;

import java.util.ArrayList;
import java.util.List;

public class MathContextFactory{
    public MathContext createMathContext(){
        List<Function> functions = createFunctions();
        return new MathContext(new TokenTypeFactory().createTokenTypes(functions), createCalculatorSpecs(), functions);
    }

    private List<Function> createFunctions(){
        TokenTypeFactory tokenTypeFactory = new TokenTypeFactory();
        List<Function>   functions        = new ArrayList<>();
        //@formatter:off
        functions.add(new Sine());
        functions.add(new Cosine());
        functions.add(new DegreesToRadiansConverter());
        functions.add(new FunctionDefinitionParser(tokenTypeFactory.createTokenTypes(functions)).parse("fuck(x)=sin(rad(30))+x"));
        functions.add(new FunctionDefinitionParser(tokenTypeFactory.createTokenTypes(functions)).parse("dick(x,y)=fuck(x)*2/fuck(y)"));
        //@formatter:on
        return functions;
    }

    private List<CalculatorSpec> createCalculatorSpecs(){
        List<CalculatorSpec> calculationTypes = new ArrayList<>();
        //@formatter:off
        calculationTypes.add(new CalculatorSpec(OperatorType.ADDITION.toString(), 1,CalculatorAssociativity.LEFT));
        calculationTypes.add(new CalculatorSpec(OperatorType.SUBTRACTION.toString(), 1,CalculatorAssociativity.LEFT));
        calculationTypes.add(new CalculatorSpec(OperatorType.MULTIPLICATION.toString(), 2,CalculatorAssociativity.LEFT));
        calculationTypes.add(new CalculatorSpec(OperatorType.DIVISION.toString(), 2,CalculatorAssociativity.LEFT));
        calculationTypes.add(new CalculatorSpec(OperatorType.POWER.toString(), 3,CalculatorAssociativity.RIGHT));
        calculationTypes.add(new CalculatorSpec(OperatorType.NEGATION.toString(), 4,CalculatorAssociativity.RIGHT));
        calculationTypes.add(new CalculatorSpec(OperatorType.FACTORIAL.toString(), 5,CalculatorAssociativity.LEFT));
        calculationTypes.add(new CalculatorSpec(OperatorType.FUNCTION.toString(), 6,CalculatorAssociativity.NONE));
        //@formatter:on
        return calculationTypes;
    }
}
