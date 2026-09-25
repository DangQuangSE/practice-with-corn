import SessionPanel from "@/components/SessionPanel";

export default function AccessStatesPage() {
  return (
    <>
      <h1>SEC-05 / FE-08 — Session-aware access states</h1>
      <p>Trang này minh họa trạng thái giao diện. Java API mới là nơi quyết định quyền truy cập.</p>
      <SessionPanel />
      <section aria-labelledby="state-heading">
        <h2 id="state-heading">Các trạng thái cần luyện</h2>
        <ul>
          <li>401 — chưa đăng nhập hoặc session hết hạn.</li>
          <li>403 — đã đăng nhập nhưng thiếu quyền.</li>
          <li>200 — được phép; vẫn phải kiểm tra quyền sở hữu ở backend.</li>
        </ul>
      </section>
    </>
  );
}
