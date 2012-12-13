/*

 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.springsource.html5expense.redis.services;

public class KeyUtils {
    public static final String EXPENSE_TYPE_KEY_PREFIX = "expenseType:";
    public static final String EXPENSE_KEY_PREFIX = "expense:";
    public static final String EXPENSE_TYPE_COUNT = "expenseTypeCount";
    public static final String EXPENSE_COUNT = "expenseCount";

    public static String expenseKey(Long id) {
        return EXPENSE_KEY_PREFIX + id;
    }

    public static String expenseTypeKey(Long id) {
        return EXPENSE_TYPE_KEY_PREFIX + id;
    }
}
