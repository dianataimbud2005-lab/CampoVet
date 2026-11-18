Campo-Vet - GA7-220501096-AA2-EV01 - Codificación de módulos

MÓDULOS (priorizados con MoSCoW, categoría "Must")
1. Gestión de inventario (PRODUCTO): RF09 registrar, RF10 actualizar, RF11 eliminar, RF12 consultar
2. Gestión de gastos (GASTO):        RF05 registrar, RF06 consultar, RF07 modificar, RF08 eliminar
   Relación del diagrama de clases: un Usuario registra muchos Gastos (Gasto -> Usuario -> Rol).

REQUISITOS: JDK 17+, MySQL, mysql-connector-j (.jar en lib/ y en el classpath)

PASOS
1. Ejecutar sql/script_campovet.sql en MySQL (una sola vez).
2. Ajustar la clave de MySQL en src/campovet/conexion/ConexionBD.java.
3. Ejecutar campovet.principal.Main (el usuario de prueba tiene ID 1).

ESTRUCTURA DE PAQUETES
campovet.conexion  -> ConexionBD (JDBC)
campovet.modelo    -> Rol, Usuario, Producto, Gasto
campovet.dao       -> UsuarioDAO, ProductoDAO, GastoDAO (CRUD)
campovet.vista     -> Entrada, MenuProducto, MenuGasto
campovet.principal -> Main

ESTÁNDARES: UpperCamelCase en clases, lowerCamelCase en atributos y métodos,
constantes en MAYUSCULAS_CON_GUION_BAJO, atributos private, 4 espacios de indentación.

Repositorio Git: PEGAR_AQUI_EL_ENLACE
