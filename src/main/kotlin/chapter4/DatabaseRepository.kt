package chapter4

import org.springframework.data.jpa.repository.JpaRepository

interface DatabaseRepository : JpaRepository<Product, String>
