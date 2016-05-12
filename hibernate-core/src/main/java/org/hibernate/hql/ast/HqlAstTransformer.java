package org.hibernate.hql.ast;

import org.hibernate.engine.query.spi.QueryPlanCache;
import org.hibernate.hql.internal.antlr.HqlTokenTypes;
import org.hibernate.hql.internal.ast.HqlASTFactory;
import org.hibernate.hql.internal.ast.util.ASTPrinter;

import antlr.collections.AST;

/**
 * This interface allows to modify the HQL AST before it is turned into SQL.
 */
public interface HqlAstTransformer {

	/**
	 * Apply transformations to the HQL AST before it is turned into SQL. <br/>
	 * <br/>
	 * ***WARNING*** Any transformation performed on the AST won't affect the HQL Query Plan key ({@link QueryPlanCache}
	 * inner class HQLQueryPlanKey) which is based on four factor:
	 * <ol>
	 * <li>The HQL query string (the one than generated the input AST)</li>
	 * <li>Whether it is a "shallow" query or not</li>
	 * <li>The name of the enabled filters</li>
	 * <li>The value of the parameters of the enabled filters</li>
	 * </ol>
	 * Therefore if your changes vary based on contextual data, you need to make sure one of the above factors also
	 * changes based on the contextual data, otherwise a previously generated query plan in another context might be
	 * used.
	 * 
	 * @param hqlAST the HQL Abstract Syntax Tree to which transformations can be applied. This AST nodes' types are
	 * defined in {@link HqlTokenTypes}. To create new nodes, use an instance of {@link HqlASTFactory}. To pretty-print
	 * the AST, use an instance of {@link ASTPrinter}.
	 */
	void transform(AST hqlAST);
}
