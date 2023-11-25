import ProductItem from './ProductItem';

function ProductList({ column, list = [] }) {
    const width = `${100 / column}%`;

    const renderList = () => list.map((product, index) => <ProductItem key={index} width={width} data={product} />);
    return <>{renderList()}</>;
}

export default ProductList;
