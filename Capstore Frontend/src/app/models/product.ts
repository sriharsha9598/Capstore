export class Product{
    productId: number;
    productName: string;
    productImage: string;
    productPrice: number;
    discount:number;
    productRating: number;
    noOfViews: number;
    quantity : number;
    productBrand: string;
    noOfProducts: number;
    productInfo:string;
    subCategory: {
        id: number;
        categoryName: string;
    }
    productActivated: boolean;
    merchant: {
        username: string;
        name: string;
        phoneNo: number;
        alternatePhoneNo: number;
        alternateEmail: string;
        gender: string;
        rating: number;
        invites: [];
        deleted: boolean;
        thirdParty: boolean;
    }
    featured: boolean;

}