"use client";

export default function ItemForm() {
  return (
    <form onSubmit={(event) => event.preventDefault()}>
      <label htmlFor="item-name">Tên item</label>
      <input id="item-name" name="name" required maxLength={80} />

      <label htmlFor="item-price">Giá</label>
      <input id="item-price" name="price" type="number" min="0" step="0.01" required />

      <button type="submit">Tạo item</button>
      <p role="status" aria-live="polite">TODO: expose pending/success state and prevent duplicate submit.</p>
      <p role="alert">TODO: display backend field errors without losing keyboard focus.</p>
    </form>
  );
}
