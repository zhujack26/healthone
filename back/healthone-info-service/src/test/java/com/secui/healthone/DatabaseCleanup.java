package com.secui.healthone;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.google.common.base.CaseFormat;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.persistence.metamodel.EntityType;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 해당 코드는 스프링 부트 애플리케이션에서 데이터베이스를 초기화하는 클래스인 DatabaseCleanup 입니다. <br>
 * 이 클래스는 InitializingBean 인터페이스를 구현하고, afterPropertiesSet() 메소드를 오버라이드하여
 * 애플리케이션 시작 시, 데이터베이스에서 관리하는 엔티티 클래스들의 이름을 수집하고,
 * execute() 메소드를 호출하면 수집한 엔티티들의 데이터를 삭제하고 초기화합니다. <br>
 * EntityManager를 사용하여 데이터베이스에서 모든 엔티티 클래스들의 정보를 가져온 후,
 * isEntity() 메소드를 통해 Entity 어노테이션이 붙어 있는 엔티티 클래스인지 확인합니다. <br>
 * 그리고 hasTableAnnotation() 메소드를 통해 Table 어노테이션이 붙어 있는지 확인합니다. <br>
 * tableNames 리스트에 저장된 엔티티 클래스들의 테이블명을 가져와서 execute() 메소드를 호출하면,
 * 해당 테이블의 데이터를 삭제하고, ALTER TABLE 쿼리를 실행하여 ID 컬럼을 초기화합니다. <br>
 * 이 클래스는 보통 테스트 코드에서 사용되며, 데이터베이스 상태를 초기화하여 테스트를 수행할 때,
 * 이전 테스트에서 남은 데이터가 영향을 주지 않도록 하는 것이 목적입니다.
 */

@Component
public class DatabaseCleanup implements InitializingBean {
    @PersistenceContext
    private EntityManager entityManager;

    private List<String> tableNames;

    @Override
    public void afterPropertiesSet() {
        final Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
        tableNames = entities.stream()
                .filter(e -> isEntity(e) && hasTableAnnotation(e))
                .map(e -> {
                    String tableName = e.getJavaType().getAnnotation(Table.class).name();
                    return tableName.isBlank() ? CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, e.getName()) : tableName;
                })
                .collect(Collectors.toList());

        final List<String> entityNames = entities.stream()
                .filter(e -> isEntity(e) && !hasTableAnnotation(e))
                .map(e -> CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, e.getName()))
                .collect(Collectors.toList());

        tableNames.addAll(entityNames);
    }

    private boolean isEntity(final EntityType<?> e) {
        return null != e.getJavaType().getAnnotation(Entity.class);
    }

    private boolean hasTableAnnotation(final EntityType<?> e) {
        return null != e.getJavaType().getAnnotation(Table.class);
    }

    @Transactional
    public void execute() {
        entityManager.flush();
        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE").executeUpdate();

        for (final String tableName : tableNames) {
            entityManager.createNativeQuery("TRUNCATE TABLE " + tableName).executeUpdate();
            entityManager.createNativeQuery("ALTER TABLE " + tableName + " ALTER COLUMN ID RESTART WITH 1").executeUpdate();
        }

        entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate();
    }

}
