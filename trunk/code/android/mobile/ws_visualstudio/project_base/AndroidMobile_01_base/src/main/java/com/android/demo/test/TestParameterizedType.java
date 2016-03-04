package com.android.demo.test;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by Kevin.young  on 2015/9/11.
 * Lately modify by Kevin.young  on 2015/9/11.
 * Copyright @ 1996-2015 Kevin Corporation, All Rights Reserved
 * <p/>
 * <p/>
 * <p/>
 * <p/>
 * Type variables have an optional bound, T & I1 ... In. The bound consists
 * of either a type variable, or a class or interface type T possibly followed
 * by further interface types I1 , ..., In. If no bound is given for a type
 * variable, Object is assumed. It is a compile-time error if any of the types
 * I1 ... In is a class type or type variable. The erasures (§4.6) of all
 * constituent types of a bound must be pair- wise different, or a compile-time
 * error occurs. The order of types in a bound is only significant in that the
 * erasure of a type variable is determined by the first type in its bound,
 * and that a class type or type variable may only appear in the first position.
 * A type variable may not at the same time be a subtype of two interface
 * types which are different parameterizations of the same generic interface.
 * See section §6.3 for the rules defining the scope of type variables.
 * The members of a type variable X with bound T & I1 ... In are the members
 * of the intersection type (§4.9) T & I1 ... In appearing at the point where
 * the type variable is declared.
 */
public class TestParameterizedType {


    public interface INTE_BASE<T> {

    }


    public interface INTE_BASE_A<V> extends INTE_BASE {

    }

    public interface INTE_BASE_B<A> extends INTE_BASE {

    }


    public interface IA {

    }

    public interface IB {

    }

    public interface IC {

    }

    public interface ID {

    }


    public class CLASS_A {

    }

    public class CLASS_B {

    }

    /**
     * A type variable (§4.4) is an unqualified identifier.
     * Type variables are introduced by generic class declarations (§8.1.2)
     * generic interface declarations (§9.1.2) generic method declarations
     * (§8.4.4) and by generic constructor declarations (§8.8.4).
     *
     * @param <T>
     * @param <IB>
     * @param <IC>
     * @param <ID>
     */
    public class CA<PARAMS, T extends CA & IA, PARAMS_A, PARAMS_B, PARAMS_C, PARAMS_D> {

        public <A extends CLASS_A & INTE_BASE_A & INTE_BASE_B, IC, ID> CA() {
            Class<A> clazz3;
        }

        public <C extends CLASS_A & IA & IB & IC & ID> void test(C c) {
            Class<C> clazz2;
        }

        public <C extends CLASS_A & IA, PARAMS_A, PARAMS_B, PARAMS_C> void test2(C c) {
            Class<C> clazz1;
            Class<PARAMS_A> clazz2;
            Class<PARAMS_B> clazz3;
            Class<PARAMS_C> clazz4;

        }

        public <PARAMS_A, PARAMS_B, PARAMS_C, PARAMS_D> void test3(PARAMS_A a, PARAMS_B b, PARAMS_C c, PARAMS_D d) {

        }

        public Class<T> clazz;
//        public Class<PARAMS2> clazz2;
//        public Class<ID> clazz3;
//        public Class<IC> clazz4;

    }

    public class CB<PARAMS_A, PARAMS_B, PARAMS_C, PARAMS_D> {

        CB<Object, ?, ? extends Object, ? super Object> objectCB;
//        Collection<E> collection;


    }


//    private <T extends CA & IA,IB,IC > void test(T t) {
//
//    }

    private <T extends IA, IB> void test(T t) {

    }


    private <T extends IC & IA, IB> void test(T t) {

    }

    private <T extends CA & IA, IB> void test(T t) {

    }


    /**
     *
     * A parameterized type consists of a class or interface name C and an actual
     type argument list <T1 , ... , Tn>. It is a compile time error if C is not the
     name of a generic class or interface, or if the number of type arguments in the
     actual type argument list differs from the number of declared type parameters of
     C. In the following, whenever we speak of a class or interface type, we include the
     generic version as well, unless explicitly excluded. Throughout this section, let A1
     , ... , An be the formal type parameters of C, and let be Bi be the declared
     bound of Ai. The notation [Ai := Ti] denotes substitution of the type variable Ai
     with the type Ti, for , and is used throughout this specification.
     Let P = G<T1, ..., Tn> be a parameterized type. It must be the case that,
     after P is subjected to capture conversion (§5.1.10) resulting in the type G<X1,
     ..., Xn>, for each actual type argument Xi, , Xi <: Bi[A1 := X1, ..., An :=
     Xn] (§4.10), or a compile time error occurs.



     Example: Parameterized types.
     Vector<String>
     Seq<Seq<A>>
     Seq<String>.Zipper<Integer>
     Collection<Integer>
     Pair<String,String>
     // Vector<int> -- illegal, primitive types cannot be arguments
     // Pair<String> -- illegal, not enough arguments
     // Pair<String,String,String> -- illegal, too many arguments
     1 ≤ i ≤ n
     1 ≤ i ≤ n
     4.5.1 Type Arguments and Wildcards TYPES, VALUES, AND VARIABLES
     52
     Two parameterized types are provably distinct if either of the following conditions
     hold:
     • They are invocations of distinct generic type declarations.
     • Any of their type arguments are provably distinct.
     */

    /**
     * 1. 基本数据类型.int,char,short,long,float,double，byte
     * 2. 引用类型。类和接口。数组
     * <p/>
     * <p/>
     * 1. TypeParameter 类型参数： 包含类型变量 类型边界(可选）
     * example :   T extends CLASS_A & INTE_A & INTE_B
     * 2. 类型边界： 第一个必须是类类型或者接口类型, & 之后的必须是接口
     * example: extends CLASS_A & INTE_A & INTE_B
     * 3. 类型变量：
     * example: T
     * 4. Parameterized Types :参数化类型
     * <p/>
     * Example: Parameterized types.
     * Vector<String>
     * Seq<Seq<A>>
     * Seq<String>.Zipper<Integer>
     * Collection<Integer>
     * Pair<String,String>
     * <p/>
     * 5 . TypeArguments 实际类型参数和通配符。
     * example :  <Object,?,? extends Object,? super Object>
     * 一般用于类中的类型参数列表的实例参数的传递。
     * public class CB<PARAMS_A, PARAMS_B, PARAMS_C, PARAMS_D> {
     * CB<Object, ?, ? extends Object, ? super Object> objectCB;
     * }
     */


    //raw Type
    class Outer<T> {
        class Inner<S> {
            S s;
        }
    }

    class Cell<E> {
        E value;

        Cell(E v) {
            value = v;
        }

        E get() {
            return value;
        }

        void set(E v) {
            value = v;
        }
    }


    public void test() {
//        Outer.Inner<Double> x = null; // illegal
//        Double d = x.s;

//        Outer<Integer>.Inner x = null; // illegal

        Outer<Object>.Inner<Double> x = null;
        Double d = x.s;

        Cell x2 = new Cell<String>("abc");
        Object value = x2.value; // OK, has type Object
        x2.get(); // OK, has type Object
        x2.set("def"); // unchecked warning
    }

//    it is not possible to access Inner as partially raw type (a "rare" type)
//    Outer.Inner<Double> x = null; // illegal
//    Double d = x.s;
}

class NonGeneric {
    Collection<Number> myNumbers() {
        return null;
    }
}


abstract class RawMembers<T> extends NonGeneric implements Collection<
        String> {
    static Collection<NonGeneric> cng =
            new ArrayList<NonGeneric>();

    public static void main(String[] args) {
        RawMembers rw = null;
        Collection<Number> cn = rw.myNumbers(); // ok
        Iterator<String> is = rw.iterator(); // unchecked warning
        Collection<NonGeneric> cnn = rw.cng; // ok - static member
    }
}
