import com.thoughtworks.kotlin_basic.application.ProductService
import com.thoughtworks.kotlin_basic.util.PrintUtil

fun main(args: Array<String>) {
    println("Hello World!")
    println("Program arguments: ${args.joinToString()}")

    val headers = listOf("ID", "Name", "SKU", "Quantity", "Original Price", "Show Price", "Zone", "Type", "Image")
    val productService = ProductService()
    val productList = productService.getProductList() ?: emptyList()
    val inventoriesList = productService.getInventoriesList() ?: emptyList()
    val rows = ProductService().getProductDtoList(productList, inventoriesList).map { it.convertToList() }

    val printUtil = PrintUtil()
    printUtil.printTable(headers, rows)
}
