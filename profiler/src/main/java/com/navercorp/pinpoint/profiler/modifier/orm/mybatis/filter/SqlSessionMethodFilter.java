package com.nhn.pinpoint.profiler.modifier.orm.mybatis.filter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.nhn.pinpoint.bootstrap.instrument.MethodInfo;
import com.nhn.pinpoint.bootstrap.instrument.MethodFilter;

/**
 * @author Hyun Jeong
 */
public class SqlSessionMethodFilter implements MethodFilter {

	private static final boolean TRACK = false;
	private static final boolean DO_NOT_TRACK = true;
	
	private static final Set<String> WHITE_LIST_API = createWhiteListApi();
	
	private static Set<String> createWhiteListApi() {
		return new HashSet<String>(Arrays.asList(
				"selectOne",
				"selectList",
				"selectMap",
				"select",
				"insert",
				"update",
				"delete"
//				"commit",
//				"rollback",
//				"flushStatements",
//				"close",
//				"getConfiguration",
//				"getMapper",
//				"getConnection"
		));
	}

	@Override
	public boolean filter(MethodInfo ctMethod) {
		if (WHITE_LIST_API.contains(ctMethod.getName())) {
			return TRACK;
		}
		return DO_NOT_TRACK;
	}
	
}
