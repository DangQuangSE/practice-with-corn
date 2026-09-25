import ItemForm from "@/components/ItemForm";

export default function ItemsPage() {
  // Server Component by default. TODO FE-03/04: fetch from Java using the shared contract.
  return (
    <>
      <h1>Items</h1>
      <section aria-labelledby="list-heading">
        <h2 id="list-heading">Danh sách</h2>
        <p role="status">TODO: render loading, empty, success, error, and not-found states.</p>
      </section>
      <section aria-labelledby="create-heading">
        <h2 id="create-heading">Tạo item</h2>
        <ItemForm />
      </section>
    </>
  );
}
