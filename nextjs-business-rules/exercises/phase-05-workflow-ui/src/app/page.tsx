"use client";

import { FormEvent, useEffect, useMemo, useState } from "react";

type OrderStatus = "PENDING" | "COMPLETED" | "CANCELLED";
type OrderSummary = { status: OrderStatus; orderCount: number; totalAmount: string; averageAmount: string };
type ImportPreview = { accepted: number; rejected: number; errors: Array<{ rowNumber: number; field: string; safeMessage: string }> };

const mockReport: OrderSummary[] = [
  { status: "PENDING", orderCount: 3, totalAmount: "125.50", averageAmount: "41.83" },
  { status: "COMPLETED", orderCount: 8, totalAmount: "840.00", averageAmount: "105.00" },
];

export default function WorkflowPracticePage() {
  const [status, setStatus] = useState("ALL");
  const [search, setSearch] = useState("");
  const [pending, setPending] = useState(false);
  const [importPreview, setImportPreview] = useState<ImportPreview | null>(null);
  const [message, setMessage] = useState("");

  useEffect(() => {
    const query = new URLSearchParams(window.location.search);
    setStatus(query.get("status") ?? "ALL");
    setSearch(query.get("q") ?? "");
  }, []);

  const visibleReport = useMemo(
    () => mockReport.filter((row) => status === "ALL" || row.status === status),
    [status],
  );

  function updateQuery(nextStatus: string, nextSearch: string) {
    const query = new URLSearchParams();
    if (nextStatus !== "ALL") query.set("status", nextStatus);
    if (nextSearch.trim()) query.set("q", nextSearch.trim());
    const suffix = query.size ? `?${query.toString()}` : "";
    window.history.replaceState(null, "", `${window.location.pathname}${suffix}`);
  }

  async function onSearch(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();
    setPending(true);
    setMessage("");
    updateQuery(status, search);
    try {
      // TODO FE-09: replace mock filtering with the typed API contract; retain URL state on refresh/back navigation.
      await new Promise((resolve) => window.setTimeout(resolve, 150));
      setMessage("Đã tải báo cáo mẫu.");
    } catch {
      setMessage("Không thể tải dữ liệu. Hãy thử lại.");
    } finally {
      setPending(false);
    }
  }

  async function onCsvSelected(event: FormEvent<HTMLInputElement>) {
    const file = event.currentTarget.files?.[0];
    setImportPreview(null);
    if (!file) return;
    if (!file.name.toLowerCase().endsWith(".csv") || file.size > 2 * 1024 * 1024) {
      setMessage("Chọn tệp CSV không quá 2 MiB.");
      return;
    }
    setMessage("Đang phân tích bản xem trước...");
    // TODO FE-15: upload to DATA-01 and render its sanitized row-level response; never retry automatically.
    await new Promise((resolve) => window.setTimeout(resolve, 150));
    setImportPreview({
      accepted: 2,
      rejected: 1,
      errors: [{ rowNumber: 4, field: "email", safeMessage: "Giá trị không hợp lệ" }],
    });
    setMessage("Bản xem trước dữ liệu mẫu — chưa ghi dữ liệu.");
  }

  async function toggleOptimisticStatus() {
    const previous = status;
    const next = previous === "CANCELLED" ? "PENDING" : "CANCELLED";
    setStatus(next);
    updateQuery(next, search);
    // TODO FE-10: optimistically update one row, call Java, and restore the previous row on API failure.
    setMessage("Trạng thái giao diện đã đổi theo dữ liệu mẫu.");
  }

  return (
    <>
      <header>
        <p className="muted">Phase 05 · FE-09, FE-10, FE-12, FE-14, FE-15</p>
        <h1>Luyện workflow và trạng thái giao diện</h1>
        <p>Module độc lập có mock data; Java API không cần chạy để thử các màn hình.</p>
      </header>

      <section aria-labelledby="report-heading">
        <h2 id="report-heading">FE-14 · Báo cáo đơn hàng</h2>
        <form onSubmit={onSearch}>
          <label>Trạng thái
            <select value={status} onChange={(event) => setStatus(event.target.value)}>
              <option value="ALL">Tất cả</option>
              <option value="PENDING">Đang xử lý</option>
              <option value="COMPLETED">Hoàn tất</option>
              <option value="CANCELLED">Đã hủy</option>
            </select>
          </label>
          <label>Tìm kiếm
            <input value={search} onChange={(event) => setSearch(event.target.value)} placeholder="Mã hoặc tên" />
          </label>
          <button type="submit" disabled={pending}>{pending ? "Đang tải…" : "Lọc báo cáo"}</button>
        </form>
        <button type="button" onClick={toggleOptimisticStatus}>Thử đổi trạng thái mẫu (FE-10)</button>
        <p className="muted" role="status" aria-live="polite">{message}</p>
        {visibleReport.length === 0 ? <p>Không có dữ liệu phù hợp.</p> : (
          <table>
            <caption>Tổng hợp đơn hàng theo trạng thái (dữ liệu mẫu)</caption>
            <thead><tr><th scope="col">Trạng thái</th><th scope="col">Số đơn</th><th scope="col">Tổng tiền</th><th scope="col">Trung bình</th></tr></thead>
            <tbody>{visibleReport.map((row) => (
              <tr key={row.status}><th scope="row">{row.status}</th><td>{row.orderCount}</td><td>{row.totalAmount}</td><td>{row.averageAmount}</td></tr>
            ))}</tbody>
          </table>
        )}
      </section>

      <section aria-labelledby="import-heading">
        <h2 id="import-heading">FE-15 · CSV import preview (Extension)</h2>
        <p id="csv-help" className="muted">Tối đa 2 MiB. Chọn mode import trước khi nối API; dữ liệu mẫu hiện không được lưu.</p>
        <label>Tệp CSV
          <input aria-describedby="csv-help" type="file" accept=".csv,text/csv" onInput={onCsvSelected} />
        </label>
        {importPreview && <>
          <p role="status">Hợp lệ: {importPreview.accepted}; lỗi: {importPreview.rejected}</p>
          <ul>{importPreview.errors.map((error) => <li key={`${error.rowNumber}-${error.field}`}>Dòng {error.rowNumber}, {error.field}: {error.safeMessage}</li>)}</ul>
        </>}
      </section>
    </>
  );
}
